package com.medvedev.fda.client.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class FdaResponseDto {
    private List<FdaRecordDto> results;

    @Data
    @Accessors(chain = true)
    public static class FdaRecordDto {
        private String application_number;
        private OpenfdaDto openfda;
        private List<FdaProductDto> products;

        @Data
        @Accessors(chain = true)
        public static class OpenfdaDto {

            private List<String> brand_name;
            private List<String> manufacturer_name;
            private List<String> substance_name;
        }

        @Data
        @Accessors(chain = true)
        public static class FdaProductDto {
            private String product_number;
        }
    }
}