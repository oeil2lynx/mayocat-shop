package org.mayocat.shop.configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;

import com.yammer.dropwizard.config.Configuration;

public class MayocatShopConfiguration extends Configuration
{
    @Valid
    @NotNull
    @JsonProperty
    private DataSourceConfiguration dataSource = new DataSourceConfiguration();
    
    public DataSourceConfiguration getDataSourceConfiguration()
    {
        return dataSource;
    }
}