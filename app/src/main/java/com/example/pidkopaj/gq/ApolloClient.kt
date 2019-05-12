package com.example.pidkopaj.gq

import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient

fun setupApollo(): ApolloClient {
    val okHttp = OkHttpClient
            .Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val builder = original.newBuilder().method(original.method(),
                        original.body())
                chain.proceed(builder.build())
            }
            .build()
    return ApolloClient.builder()
            .serverUrl("https://api.graph.cool/simple/v1/cjvfil1ks7b6s012952h2tt69")
            .okHttpClient(okHttp)
            .build()
}