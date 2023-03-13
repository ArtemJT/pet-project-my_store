package com.example.my_store_spring.configuration;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DropboxConfig {

    private static final String ACCESS_TOKEN =
            "sl.BajmMOn1M0xBHSkxmuRt8PFo36tlBaA3fz4Sj6pTsmRXjB6I42imb__F-7OyAWMdyylvIpHM7R8F_0UWAtMsvIJqpNM_iwmoRrlutbvRe8-MIKLTDRpuwC0tqXJ2_Pubs8UTqfrZ";

    @Bean
    public DbxClientV2 dbxClientV2() {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        return new DbxClientV2(config, ACCESS_TOKEN);
    }
}
