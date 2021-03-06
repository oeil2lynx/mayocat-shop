package org.mayocat.shop.catalog.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.mayocat.model.AbstractLocalizedEntity;
import org.mayocat.model.Addon;
import org.mayocat.model.HasAddons;
import org.mayocat.model.HasFeaturedImage;
import org.mayocat.model.HasModel;
import org.mayocat.model.PerhapsLoaded;
import org.mayocat.model.annotation.DoNotIndex;
import org.mayocat.model.annotation.Localized;
import org.mayocat.model.annotation.Index;

import com.google.common.base.Objects;
import com.google.common.base.Optional;

@Index
public class Product extends AbstractLocalizedEntity implements HasAddons, HasModel, HasFeaturedImage, Purchasable
{
    private static final long serialVersionUID = 6998229869430511994L;

    @DoNotIndex
    private UUID id;

    private Boolean onShelf;

    @NotNull
    @Size(min = 1)
    private String slug;

    @Localized
    @NotNull
    @Size(min = 1)
    private String title;

    @Localized
    private transient String description;

    private BigDecimal price;

    private Integer stock;

    @DoNotIndex
    private UUID featuredImageId;

    private transient PerhapsLoaded<List<Addon>> addons = PerhapsLoaded.notLoaded();

    @DoNotIndex
    private Optional<String> model = Optional.absent();

    public Product()
    {
    }

    public Product(UUID id)
    {
        this.id = id;
    }

    public String getSlug()
    {
        return slug;
    }

    public void setSlug(String slug)
    {
        this.slug = slug;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public UUID getId()
    {
        return this.id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public Boolean getOnShelf()
    {
        return onShelf;
    }

    public void setOnShelf(Boolean onShelf)
    {
        this.onShelf = onShelf;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public BigDecimal getUnitPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    @Override
    public PerhapsLoaded<List<Addon>> getAddons()
    {
        return this.addons;
    }

    public void setAddons(List<Addon> addons)
    {
        this.addons = new PerhapsLoaded<List<Addon>>(addons);
    }

    public void setModel(String model)
    {
        this.model = Optional.fromNullable(model);
    }

    @Override
    public Optional<String> getModel()
    {
        return model;
    }

    @Override
    public UUID getFeaturedImageId()
    {
        return this.featuredImageId;
    }

    public void setFeaturedImageId(UUID featuredImageId)
    {
        this.featuredImageId = featuredImageId;
    }

    public Integer getStock()
    {
        return stock;
    }

    public void setStock(Integer stock)
    {
        this.stock = stock;
    }

    ////////////////////////////////////////////////

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;

        return Objects.equal(this.title, other.title)
                && Objects.equal(this.slug, other.slug)
                && Objects.equal(this.onShelf, other.onShelf)
                && Objects.equal(this.price, other.price)
                && Objects.equal(this.stock, other.stock)
                && Objects.equal(this.featuredImageId, other.featuredImageId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(
                this.slug,
                this.title,
                this.onShelf,
                this.price,
                this.stock,
                this.featuredImageId
        );
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this)
                .addValue(this.title)
                .addValue(this.slug)
                .addValue(this.onShelf)
                .toString();
    }
}
