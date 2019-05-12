package com.example.pidkopaj.gq

import GetKid
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submit.setOnClickListener {
            getKid(nameEdit.text.toString(), surnameEdit.text.toString())
        }

    }

    private fun getKid(firstName: String = "Lara", lastName: String = "Bara") {
        val client = setupApollo()
        client.query(GetKid
                .builder()
                .firstName(firstName)
                .lastName(lastName)
                .build())
                .enqueue(object : ApolloCall.Callback<GetKid.Data>() {
                    override fun onFailure(e: ApolloException) {
                        println(e.message.toString())
                    }
                    override fun onResponse(response: Response<GetKid.Data>) {
                        runOnUiThread {
                            adreessResult.text = response.data()!!.allKids()[0].dateOfBirth().toString()
                        }
                    }
                })
    }
}
