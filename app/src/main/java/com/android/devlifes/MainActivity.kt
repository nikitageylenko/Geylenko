package com.android.devlifes

import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.devlifes.api.Repository

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var currentPage: Int = 0
        var viewModel: MainViewModel
        val dataUrl = mutableListOf<String>()
        val dataDescription = mutableListOf<String>()
        var lastPage: Int = 0

        val wb : WebView = findViewById(R.id.gif)
        val textView : TextView = findViewById(R.id.text_view)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getGif()
        viewModel.myResponse.observe(this, Observer {response->
            if(response.isSuccessful){
                val url =  response.body()?.gifURL
                val text = response.body()?.description
                dataDescription.add(currentPage, text!!)
                textView.text = text
                dataUrl.add(currentPage, url!!)
                wb.loadUrl(url)
            }

        })

        val buttonNext = findViewById<Button>(R.id.buttonNext)
        val buttonPrev = findViewById<Button>(R.id.buttonPrev)
        buttonPrev.setEnabled(false)

        buttonNext.setOnClickListener{
            buttonPrev.setEnabled(true)
            currentPage++
            if (currentPage > lastPage){
                lastPage++
                viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
                viewModel.getGif()
                viewModel.myResponse.observe(this, Observer {response->
                    if(response.isSuccessful){
                        val text = response.body()?.description
                        dataDescription.add(currentPage, text!!)
                        textView.text = text
                        val url =  response.body()?.gifURL
                        dataUrl.add(currentPage, url!!)
                        wb.loadUrl(url)
                    }

                })
            }
            else {
                buttonPrev.setEnabled(true)
                viewModel.myResponse.observe(this, Observer {response->
                    if(response.isSuccessful){
                        textView.text = dataDescription[currentPage]
                        wb.loadUrl(dataUrl[currentPage])
                    }
                })
            }
        }

        buttonPrev.setOnClickListener{
            currentPage--
            if (currentPage == 0){
                buttonPrev.setEnabled(false)
            }
            viewModel.myResponse.observe(this, Observer {response->
                if(response.isSuccessful){
                    textView.text = dataDescription[currentPage]
                    wb.loadUrl(dataUrl[currentPage])
                }

            })
        }
    }
}





