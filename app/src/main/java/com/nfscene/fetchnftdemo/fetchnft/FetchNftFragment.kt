package com.nfscene.fetchnftdemo.fetchnft

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.nfscene.fetchnftdemo.databinding.FragmentFetchnftBinding

class FetchNftFragment: Fragment() {
    private var _binding: FragmentFetchnftBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val model: FetchNftActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFetchnftBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.getResponse().observe(viewLifecycleOwner) { response ->
            val editable = binding.responseText.text
            editable.replace(0, editable.length, response)
        }
        binding.requestButton.setOnClickListener {
            model.sendRequest(
                binding.textInput.editText?.text.toString()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}