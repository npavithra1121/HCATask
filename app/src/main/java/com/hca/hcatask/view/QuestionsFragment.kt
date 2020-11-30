package com.hca.hcatask.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.hca.hcatask.R
import com.hca.hcatask.base.BaseFragment
import com.hca.hcatask.model.QuestionItem
import com.hca.hcatask.utils.NoInternetException
import com.hca.hcatask.utils.hide
import com.hca.hcatask.utils.show
import com.hca.hcatask.view.adapter.QuestionsListAdapter
import com.hca.hcatask.viewmodel.QuestionsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class QuestionsFragment @Inject constructor() : BaseFragment() {

    lateinit var mItemAdapter: QuestionsListAdapter

    @Inject
    lateinit var mQuestionsViewModel: QuestionsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    @InternalCoroutinesApi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.setTitle(getString(R.string.questions))
        initializeViews()
        updateView()
    }

    @InternalCoroutinesApi
    private fun updateView() {
        lifecycleScope.launch {
            mQuestionsViewModel.listData.collect {
                it.let {
                    mItemAdapter.submitData(it)
                }
            }
        }
    }

    private fun initializeViews() {
        mItemAdapter = QuestionsListAdapter(block = { data: QuestionItem ->
            itemClick(data)
        })
        main_recyclerView.layoutManager = LinearLayoutManager(context)
        main_recyclerView.adapter = mItemAdapter
        mItemAdapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading ||
                    loadState.append is LoadState.Loading
            )
                showHideLoading(true)
            else {
                showHideLoading(false)
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    showToast(it.error.toString().replace(NoInternetException::class.java.name, ""))
                }

            }
        }
    }

    private fun itemClick(data: QuestionItem) {
        AnswerFragment.getInstance(
                supportFragment = parentFragmentManager,
                layout_inflate = R.id.frame_container_user, data.questionId, data.title)
    }

    private fun showHideLoading(show: Boolean) {
        if (show) {
            progress_bar?.show()
        } else {
            progress_bar?.hide()
        }
    }

    companion object {
        fun getInstance(supportFragment: FragmentManager?, layout: Int) {

            val fragmentToInflate = QuestionsFragment()
            supportFragment?.beginTransaction()
                    ?.add(
                            layout, fragmentToInflate,
                            QuestionsFragment::class.java.simpleName
                    )
                    ?.commit()
        }
    }
}