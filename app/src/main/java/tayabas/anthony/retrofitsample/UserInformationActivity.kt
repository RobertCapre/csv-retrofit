package tayabas.anthony.retrofitsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import tayabas.anthony.retrofitsample.databinding.ActivityUserinformationBinding
import tayabas.anthony.retrofitsample.model.Data

class UserInformationActivity : AppCompatActivity() {
    private lateinit var binding:  ActivityUserinformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserinformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user = intent.getParcelableExtra<Data>(EXTRA_MESSAGE)
        user.let {
            with(binding) {
                val name = "${it?.firstName} ${it?.lastName}"
                val email = it?.email
                val id = it?.id
                val url = it?.avatar
                tvId.text = "ID: ${id.toString()}"
                tvName.text = "Name: $name"
                tvEmail.text = "Email: $email"
                Glide.with(this@UserInformationActivity)
                    .load(url)
                    .into(imgAvatar)
            }
        }
    }

    companion object {
        const val EXTRA_MESSAGE = "MESSAGE"
    }
}