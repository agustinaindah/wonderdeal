package id.wonderdeal.wonderdealapps.features.menu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import id.wonderdeal.wonderdealapps.R;
import id.wonderdeal.wonderdealapps.base.BaseFragment;
import id.wonderdeal.wonderdealapps.custom.DividerItemDecoration;
import id.wonderdeal.wonderdealapps.model.Simple;
import id.wonderdeal.wonderdealapps.utils.Consts;

/**
 * Created by agustinaindah on 23/09/2017.
 */

public class ListMenuFragment extends BaseFragment {

    @BindView(R.id.rvListMenu)
    RecyclerView rvListMenu;

    @BindString(R.string.menu)
    String strMenu;

    public static ListMenuFragment newInstance() {
        Bundle args = new Bundle();
        ListMenuFragment fragment = new ListMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_menu;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(strMenu);
        //initView();
        //setupAdapter();
        initRecyclerView(initMenus());
    }

    private void initRecyclerView(List<HashMap<String, Integer>> menus){
        ListMenuAdapter adapter = new ListMenuAdapter(menus, getActivity());

        rvListMenu.setHasFixedSize(true);
        rvListMenu.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvListMenu.setAdapter(adapter);
        rvListMenu.setNestedScrollingEnabled(false);
    }

    @NonNull
    private List<HashMap<String, Integer>> initMenus() {
        List<HashMap<String, Integer>> menus = new ArrayList<>();
        menus.add(addMenu(R.string.payment, R.drawable.ic_accounting48));
        menus.add(addMenu(R.string.label_syarat, R.drawable.ic_pagefilled48));
        return menus;
    }

    private HashMap<String, Integer> addMenu(int resIdTitle, int resIdIcon) {
        HashMap<String, Integer> item = new HashMap<>();
        item.put(Consts.TITLE, resIdTitle);
        item.put(Consts.ICON, resIdIcon);
        return item;
    }

    /*private void initView() {
        rvListMenu.setHasFixedSize(true);
        rvListMenu.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        rvListMenu.addItemDecoration(itemDecoration);
    }*/

  /*  private void setupAdapter() {
         ArrayList<Simple> menu = new ArrayList<Simple>();
        menu.add(new Simple(Consts.PAYMENT, strMenuPayment));
        //personal.add(new Simple(Consts.CASHBACK, strCashback));
        ListMenuAdapter mAdapter = new ListMenuAdapter(getActivity());
        mAdapter.updateData(menu);
        rvListMenu.setAdapter(mAdapter);
    }*/
}
