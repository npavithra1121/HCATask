package com.hca.hcatask.view

import com.hca.hcatask.R
import com.hca.hcatask.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun setupViews() {
        inflateFragment()
    }

    private fun inflateFragment() {
        QuestionsFragment.getInstance(
                supportFragment = supportFragmentManager,
            layout = R.id.frame_container_user)
    }

}