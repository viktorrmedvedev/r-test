package com.medvedev.fda.client.util;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SearchExpanderTest {

    private final SearchExpander searchExpander = new SearchExpander();

    @Test
    void shouldExpandValidMapToSearchString() {
        // Given
        final var testMap = new LinkedHashMap<>();
        testMap.put("manufacturer_name", "Manufacturer1");
        testMap.put("brand_name", "Brand1");

        // When
        final var expandedValue = searchExpander.expand(testMap);

        // Then
        assertEquals("manufacturer_name:Manufacturer1+brand_name:Brand1", expandedValue);
    }

    @Test
    void shouldFailIfMapContainsWrongTypes() {
        // Given
        final var invalidMap = new HashMap<Object, Object>();
        invalidMap.put("manufacturer_name", "Manufacturer1");
        invalidMap.put(123, 456); // Invalid key and value

        // When/Then
        assertThrows(IllegalStateException.class, () -> searchExpander.expand(invalidMap));
    }

    @Test
    void shouldFailIfNotAMap() {
        // Given
        final var nonMapObject = "Not a map";

        // When/Then
        assertThrows(IllegalStateException.class, () -> searchExpander.expand(nonMapObject));
    }
}
