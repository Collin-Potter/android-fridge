package com.example.android_fridge

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ShareCompat
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android_fridge.data.Item
import com.example.android_fridge.databinding.FragmentItemDetailBinding
import com.example.android_fridge.utilities.InjectorUtils
import com.example.android_fridge.viewmodels.ItemDetailViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_item_detail.*

class ItemDetailFragment : Fragment() {

    private val args: ItemDetailFragmentArgs by navArgs()

    private val itemDetailViewModel: ItemDetailViewModel by viewModels {
        InjectorUtils.provideItemDetailViewModelFactory(requireActivity(), args.itemId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentItemDetailBinding>(
            inflater, R.layout.fragment_item_detail, container, false
        ).apply {
            viewModel = itemDetailViewModel
            lifecycleOwner = viewLifecycleOwner
            callback = object : Callback {
                override fun add(item: Item?) {
                    item?.let {
                        hideAppBarFab(fab)
                        showAppBarFab(fab_stocked)
                        itemDetailViewModel.addItemToFridge()
                        Snackbar.make(root, "Added item to fridge", Snackbar.LENGTH_LONG)
                            .show()
                    }
                }

                override fun remove(item: Item?) {
                    item?.let {
                        hideAppBarFab(fab_stocked)
                        showAppBarFab(fab)
                        itemDetailViewModel.removeItemFromFridge()
                        Snackbar.make(root, "Removed item from fridge", Snackbar.LENGTH_LONG)
                            .show()
                    }
                }
            }

            var isToolbarShown = false

            itemDetailScrollview.setOnScrollChangeListener(
                NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->

                    val shouldShowToolbar = scrollY > toolbar.height

                    if (isToolbarShown != shouldShowToolbar) {
                        isToolbarShown = shouldShowToolbar

                        appbar.isActivated = shouldShowToolbar

                        toolbarLayout.isTitleEnabled = shouldShowToolbar
                    }
                }
            )

            toolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }

            toolbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_share -> {
                        createShareIntent()
                        true
                    }
                    else -> false
                }
            }
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    @Suppress("DEPRECATION")
    private fun createShareIntent() {
        val shareText = itemDetailViewModel.item.value.let { item ->
            if (item == null) {
                ""
            } else {
                "Check out the {item.name} item in the Android Fridge app"
            }
        }
        val shareIntent = ShareCompat.IntentBuilder.from(activity)
            .setText(shareText)
            .setType("text/plain")
            .createChooserIntent()
            .apply {
                // https://android-developers.googleblog.com/2012/02/share-with-intents.html
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // If we're on Lollipop, we can open the intent as a document
                    addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                } else {
                    // Else, we will use the old CLEAR_WHEN_TASK_RESET flag
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
                }
            }
        startActivity(shareIntent)
    }

    private fun hideAppBarFab(fab: FloatingActionButton) {
        val params = fab.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as FloatingActionButton.Behavior
        behavior.isAutoHideEnabled = false
        fab.hide()
    }

    private fun showAppBarFab(fab: FloatingActionButton) {
        val params = fab.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as FloatingActionButton.Behavior
        behavior.isAutoHideEnabled = false
        fab.show()
    }

    interface Callback {
        fun add(item: Item?)
        fun remove(item: Item?)
    }
}
