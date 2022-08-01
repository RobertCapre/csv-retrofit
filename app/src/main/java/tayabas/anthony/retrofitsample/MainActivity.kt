package tayabas.anthony.retrofitsample

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import tayabas.anthony.retrofitsample.adapter.RetrofitAdapter
import tayabas.anthony.retrofitsample.data.api.ApiInterface
import tayabas.anthony.retrofitsample.data.api.RetrofitClient
import tayabas.anthony.retrofitsample.databinding.ActivityMainBinding
import tayabas.anthony.retrofitsample.model.Data

class MainActivity : AppCompatActivity() {

    private lateinit var binding:  ActivityMainBinding
    private lateinit var retrofitAdapter: RetrofitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getUserList()
    }

    fun getUserList() {
        var retrofit = RetrofitClient.getInstance()
        var apiInterface = retrofit.create(ApiInterface::class.java)
        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.getAllUsers()
                if (response.isSuccessful()) {
                    //your code for handling success response
                    // In 1st page, display the list of fetched users in format:
                    // FirstName + LastName
                    // Email Address
                    Log.d("test", "${response.body()?.data}")

                    with(binding) {
                        retrofitAdapter = RetrofitAdapter(response.body()!!.data)

                        retrofitAdapter.setOnItemClickListener {
                            val intent = Intent(this@MainActivity, UserInformationActivity::class.java).apply {
                                putExtra(UserInformationActivity.EXTRA_MESSAGE, it)
                            }
                            startActivity(intent)
                        }

                        rvListOfUser.apply {
                            adapter = retrofitAdapter
                            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                            setHasFixedSize(true)
                        }


                    }
                    // After selecting a user, open a second page to display all the details of the user
                    // Use glide for loading avatar to imageview
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        response.errorBody().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }catch (Ex:Exception){
                Log.e("Error",Ex.localizedMessage)
            }
        }

    }

}