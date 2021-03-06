package org.mayocat.shop.catalog.configuration.shop;

import javax.validation.Valid;

import org.mayocat.configuration.Configurable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @version $Id$
 */
public class ProductsSettings
{

    @Valid
    @JsonProperty
    private Configurable<Boolean> stock = new Configurable<Boolean>(true);

    @Valid
    @JsonProperty
    private Configurable<Boolean> collections = new Configurable<Boolean>(true);

    @Valid
    @JsonProperty
    private Configurable<Boolean> variants = new Configurable<Boolean>(true);

    @Valid
    @JsonProperty
    private Configurable<Boolean> priceVariesWithVariants = new Configurable<Boolean>(true);


    public Configurable<Boolean> getCollections()
    {
        return this.collections;
    }

    public Configurable<Boolean> getVariants()
    {
        return variants;
    }

    public Configurable<Boolean> getPriceVariesWithVariants()
    {
        return priceVariesWithVariants;
    }

    public Configurable<Boolean> getStock()
    {
        return stock;
    }
}
