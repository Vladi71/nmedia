package ru.netology.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import ru.netology.AndroidUtils
import ru.netology.R
import ru.netology.adapter.OnInteractionListener
import ru.netology.adapter.PostAdapter
import ru.netology.databinding.ActivityEditPostBinding
import ru.netology.databinding.ActivityEditPostBinding.inflate
import ru.netology.databinding.ActivityMainBinding
import ru.netology.databinding.ActivityNewPostBinding
import ru.netology.databinding.CardPostBinding
import ru.netology.dto.Post
import ru.netology.viewModel.PostViewModel

class MainActivity : AppCompatActivity() {
    val viewModel: PostViewModel by viewModels()
    private val newPostRequestCode = 1
    private val editPostRequestCode = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)


        val adapter = PostAdapter(object : OnInteractionListener {

            override fun onShare(post: Post) {
                viewModel.shareById(post.id)
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(intent, getString(R.string.shooser_intent_post))
                startActivity(shareIntent)
            }

            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onEdit(post: Post) {
                viewModel.edit(post)
                val intent = Intent(this@MainActivity, EditPostActivity::class.java).apply {
                    putExtra("text", post.content)
                    putExtra("video", post.contentVideo)

                }
                startActivityForResult(intent, editPostRequestCode)
            }

            override fun onCancelEdit(post: Post) {
                viewModel.cancelChange()
            }

        })
        binding.listPost.adapter = adapter
        viewModel.data.observe(this) { post ->
            adapter.submitList(post)

        }
        viewModel.edited.observe(this) { post ->
            if (post.id == 0L) {
                return@observe
            }
        }

        binding.addPostView.setOnClickListener {
            val intent = Intent(this, NewPostActivity::class.java)
            startActivityForResult(intent, newPostRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            newPostRequestCode -> {
                if (resultCode != Activity.RESULT_OK) {
                    return
                }
                data?.extras?.let {
                    val textContent = it?.get("contentText").toString()
                    val videoContent = it?.get("contentVideo").toString()
                    viewModel.changeContent(textContent, videoContent)
                    viewModel.save()

                }
            }
            editPostRequestCode -> {
                if (resultCode != Activity.RESULT_OK) {
                    return
                }
                data?.extras?.let {
                    val textContent = it?.get("edContentText").toString()
                    val videoContent = it?.get("edContentVideo").toString()
                    viewModel.changeContent(textContent, videoContent)
                    viewModel.save()
                }
            }
        }
    }
}


