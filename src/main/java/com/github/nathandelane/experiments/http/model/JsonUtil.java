package com.github.nathandelane.experiments.http.model;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

public final class JsonUtil {

  private JsonUtil() { }

  private static final Moshi moshi;

  private static final JsonAdapter<NameRequest> nameRequestJsonAdapter;

  static {
    moshi = new Moshi.Builder().build();
    nameRequestJsonAdapter = moshi.adapter(NameRequest.class);
  }

  public static NameRequest parseNameRequest(final String json) throws IOException {
    return nameRequestJsonAdapter.fromJson(json);
  }

}
