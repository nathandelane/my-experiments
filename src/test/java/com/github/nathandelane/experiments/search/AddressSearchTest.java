package com.github.nathandelane.experiments.search;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class AddressSearchTest {

  @Test
  public void simplePostalCodeRangeTest() {
    final String postalCodeLow = "84104";
    final String postalCodeHigh = "84901";

    final String testPostalCode = "84106";
    final AddressSearch.PostalCodeRange postalCodeRange = new AddressSearch.PostalCodeRange(postalCodeLow, postalCodeHigh);

    assertTrue(postalCodeRange.contains(testPostalCode));
  }

  @Test
  public void postalCodeRangeOutOfOrder() {
    final String postalCodeLow = "84300";
    final String postalCodeHigh = "84101";

    final String testPostalCode = "84104";
    final AddressSearch.PostalCodeRange postalCodeRange = new AddressSearch.PostalCodeRange(postalCodeLow, postalCodeHigh);

    assertTrue(postalCodeRange.contains(testPostalCode));
  }

  @Test
  public void testAlphaNumericPostalCodes() {
    final String postalCodeLow = "G1A1A1";
    final String postalCodeHigh = "G9Z9Z9";

    final String testPostalCode = "G1N4K3";
    final AddressSearch.PostalCodeRange postalCodeRange = new AddressSearch.PostalCodeRange(postalCodeLow, postalCodeHigh);

    assertTrue(postalCodeRange.contains(testPostalCode));
  }

  @Test
  public void testAlphaNumericPostalCodesReversed() {
    final String postalCodeLow = "G9Z9Z9";
    final String postalCodeHigh = "G1A1A1";

    final String testPostalCode = "G1N4K3";
    final AddressSearch.PostalCodeRange postalCodeRange = new AddressSearch.PostalCodeRange(postalCodeLow, postalCodeHigh);

    assertTrue(postalCodeRange.contains(testPostalCode));
  }

  @Test
  public void testAlhpaNumericPostalCodesNotInRange() {
    final String postalCodeLow = "G1A1A1";
    final String postalCodeHigh = "G9Z9Z9";

    final String testPostalCode = "H1N4K3";
    final AddressSearch.PostalCodeRange postalCodeRange = new AddressSearch.PostalCodeRange(postalCodeLow, postalCodeHigh);

    assertFalse(postalCodeRange.contains(testPostalCode));
  }

  @Test
  public void getHashForCountryCode() {
    final String[] countryCodes = new String[] {
      "AD", "AE", "AF", "AG", "AI", "AL", "AM", "AO", "AQ", "AR", "AS", "AT", "AU", "AW", "AX", "AZ", "BA", "BB", "BD",
      "BE", "BF", "BG", "BH", "BI", "BJ", "BL", "BM", "BN", "BO", "BQ", "BR", "BS", "BT", "BV", "BW", "BY", "BZ", "CA",
      "CC", "CD", "CF", "CG", "CH", "CI", "CK", "CL", "CM", "CN", "CO", "CR", "CU", "CV", "CW", "CX", "CY", "CZ", "DE",
      "DJ", "DK", "DM", "DO", "DZ", "EC", "EE", "EG", "EH", "ER", "ES", "ET", "FI", "FJ", "FK", "FM", "FO", "FR", "GA",
      "GB", "GD", "GE", "GF", "GG", "GH", "GI", "GL", "GM", "GN", "GP", "GQ", "GR", "GS", "GT", "GU", "GW", "GY", "HK",
      "HM", "HN", "HR", "HT", "HU", "ID", "IE", "IL", "IM", "IN", "IO", "IQ", "IR", "IS", "IT", "JE", "JM", "JO", "JP",
      "KE", "KG", "KH", "KI", "KM", "KN", "KP", "KR", "KW", "KY", "KZ", "LA", "LB", "LC", "LI", "LK", "LR", "LS", "LT",
      "LU", "LV", "LY", "MA", "MC", "MD", "ME", "MF", "MG", "MH", "MK", "ML", "MM", "MN", "MO", "MP", "MQ", "MR", "MS",
      "MT", "MU", "MV", "MW", "MX", "MY", "MZ", "NA", "NC", "NE", "NF", "NG", "NI", "NL", "NO", "NP", "NR", "NU", "NZ",
      "OM", "PA", "PE", "PF", "PG", "PH", "PK", "PL", "PM", "PN", "PR", "PS", "PT", "PW", "PY", "QA", "RE", "RO", "RS",
      "RU", "RW", "SA", "SB", "SC", "SD", "SE", "SG", "SH", "SI", "SJ", "SK", "SL", "SM", "SN", "SO", "SR", "SS", "ST",
      "SV", "SX", "SY", "SZ", "TC", "TD", "TF", "TG", "TH", "TJ", "TK", "TL", "TM", "TN", "TO", "TR", "TT", "TV", "TW",
      "TZ", "UA", "UG", "UM", "US", "UY", "UZ", "VA", "VC", "VE", "VG", "VI", "VN", "VU", "WF", "WS", "YE", "YT", "ZA",
      "ZM", "ZW"
    };

    final Map<String, Integer> mapOfHashes = new HashMap<>();

    boolean first = true;

    for (final String cc : countryCodes) {
      final int hash = AddressSearch.hashTwoCharCountryCode(cc);

      mapOfHashes.put(cc, hash);

      if (!first) {
        System.out.print(", ");
      }

      System.out.print(String.format("%s:%d", cc, hash));

      first = false;
    }

    final Set<Integer> setOfValues = new HashSet<>(mapOfHashes.values());
    final int sizeOfMapValues = mapOfHashes.values().size();
    final int uniqueValue = setOfValues.size();

    assertEquals(String.format("Map values: %d vs. set: %d", sizeOfMapValues, uniqueValue), sizeOfMapValues, uniqueValue);
  }

}
