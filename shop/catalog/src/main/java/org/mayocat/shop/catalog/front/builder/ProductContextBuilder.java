package org.mayocat.shop.catalog.front.builder;

import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.mayocat.addons.front.builder.AddonContextBuilder;
import org.mayocat.addons.model.AddonField;
import org.mayocat.addons.model.AddonGroup;
import org.mayocat.addons.util.AddonUtils;
import org.mayocat.configuration.general.GeneralSettings;
import org.mayocat.image.model.Image;
import org.mayocat.shop.catalog.configuration.shop.CatalogSettings;
import org.mayocat.shop.catalog.front.representation.PriceRepresentation;
import org.mayocat.shop.catalog.meta.ProductEntity;
import org.mayocat.shop.catalog.model.Product;
import org.mayocat.addons.front.builder.AddonContextBuilderHelper;
import org.mayocat.shop.front.builder.ImageContextBuilder;
import org.mayocat.shop.front.context.ContextConstants;
import org.mayocat.shop.front.util.ContextUtils;
import org.mayocat.theme.Theme;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @version $Id$
 */
public class ProductContextBuilder implements ContextConstants
{
    private CatalogSettings catalogSettings;

    private GeneralSettings generalSettings;

    private Theme theme;

    private ImageContextBuilder imageContextBuilder;

    private AddonContextBuilder addonContextBuilder;

    public ProductContextBuilder(CatalogSettings catalogSettings, GeneralSettings generalSettings,
            Theme theme)
    {
        this.catalogSettings = catalogSettings;
        this.generalSettings = generalSettings;
        this.theme = theme;

        imageContextBuilder = new ImageContextBuilder(theme);
        addonContextBuilder = new AddonContextBuilder();
    }

    public Map<String, Object> build(final Product product, List<Image> images)
    {
        Map<String, Object> productContext = Maps.newHashMap();

        productContext.put("title", ContextUtils.safeString(product.getTitle()));
        productContext.put("description", ContextUtils.safeHtml(product.getDescription()));
        productContext.put(URL, "/" + ProductEntity.PATH + "/" + product.getSlug());
        productContext.put(SLUG, product.getSlug());

        // Prices
        if (product.getUnitPrice() != null) {
            final Locale locale = generalSettings.getLocales().getMainLocale().getValue();
            final Currency currency = catalogSettings.getCurrencies().getMainCurrency().getValue();
            productContext.put("unitPrice", new PriceRepresentation(product.getUnitPrice(), currency, locale));
        }

        Map<String, Object> imagesContext = Maps.newHashMap();
        List<Map<String, String>> allImages = Lists.newArrayList();

        Image featuredImage = null;

        if (images.size() > 0) {
            for (Image image : images) {
                if (featuredImage == null && image.getAttachment().getId().equals(product.getFeaturedImageId())) {
                    featuredImage = image;
                }
                allImages.add(imageContextBuilder.createImageContext(image, image == featuredImage));
            }
            if (featuredImage == null) {
                // If no featured image has been set, we use the first image in the array.
                featuredImage = images.get(0);
            }
            imagesContext.put("featured", imageContextBuilder.createImageContext(featuredImage, true));
        } else {
            // Create placeholder image
            Map<String, String> placeholder = imageContextBuilder.createPlaceholderImageContext(true);
            imagesContext.put("featured", placeholder);
            allImages = Arrays.asList(placeholder);
        }

        imagesContext.put("all", allImages);
        productContext.put("images", imagesContext);

        // Addons

        if (product.getAddons().isLoaded()) {
            Map<String, AddonGroup> themeAddons = theme.getAddons();
            productContext.put("theme_addons", addonContextBuilder.build(themeAddons, product.getAddons().get()));
        }

        return productContext;
    }
}
