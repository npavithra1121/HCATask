package com.hca.hcatask.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hca.hcatask.R
import com.hca.hcatask.base.BaseFragment
import com.hca.hcatask.domain.NetworkStatus
import com.hca.hcatask.utils.hide
import com.hca.hcatask.utils.show
import com.hca.hcatask.view.adapter.AnswersListAdapter
import com.hca.hcatask.viewmodel.AnswerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
class AnswerFragment @Inject constructor() : BaseFragment() {
    @Inject
    lateinit var mItemAdapter: AnswersListAdapter

    @Inject
    lateinit var answerViewModel: AnswerViewModel

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
        activity?.setTitle(getString(R.string.answers))
        tv_question.show()
        tv_question.text = arguments?.getString(KEY_QUESTION)
        initializeViews()
        observeInvoicesList()
        arguments?.getInt(KEY_QID)?.let { answerViewModel.getAnswersData(it) }
    }

    private fun initializeViews() {
        main_recyclerView.layoutManager = LinearLayoutManager(context)
        main_recyclerView.adapter = mItemAdapter
    }

    private fun observeInvoicesList() {
        answerViewModel.getAnswerLiveData().observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is NetworkStatus.Success -> {
                        showHideLoading(false)
                        it.data.let {
                            if (::mItemAdapter.isInitialized) {
                                mItemAdapter.submitList(it.items)
                                mItemAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                    is NetworkStatus.Loading -> showHideLoading(it.status)
                    is NetworkStatus.CustomSignal -> {
                        showHideLoading(false)
                        showToast(it.message)
                    }

                    is NetworkStatus.Error -> {
                        showHideLoading(false)
                        showToast(it.error)
                    }

                    else -> {
                    }
                }
            }

        })
    }

    private fun showHideLoading(show: Boolean) {
        if (show) {
            progress_bar?.show()
        } else {
            progress_bar?.hide()
        }
    }

    companion object {
        const val KEY_QID: String = "key_qid"
        const val KEY_QUESTION: String = "key_question"
        fun getInstance(supportFragment: FragmentManager?, layout_inflate: Int, questionId: Int, question: String) {
            val args = Bundle()
            args.putInt(KEY_QID, questionId)
            args.putString(KEY_QUESTION, question)
            val fragmentInflate = AnswerFragment()
            fragmentInflate.arguments = args
            supportFragment?.beginTransaction()
                    ?.replace(
                            layout_inflate, fragmentInflate,
                            AnswerFragment::class.java.simpleName
                    )?.addToBackStack(AnswerFragment::class.java.simpleName)
                    ?.commit()
        }
    }
}