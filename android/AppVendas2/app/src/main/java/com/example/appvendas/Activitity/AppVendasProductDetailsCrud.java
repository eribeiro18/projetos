package com.example.appvendas.Activitity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.appvendas.Adapter.CRUDProductListRVAdapter;
import com.example.appvendas.Entity.Product;
import com.example.appvendas.Helpers.Handler.ImageHandler;
import com.example.appvendas.Helpers.Interface.OnProductDeleteListener;
import com.example.appvendas.Helpers.Interface.OnProductEditListener;
import com.example.appvendas.Helpers.Singleton.EventSingleton;
import com.example.appvendas.Helpers.Interface.EventListener;
import com.example.appvendas.Model.ProductViewModel;
import com.example.appvendas.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppVendasProductDetailsCrud extends AppCompatActivity implements OnProductDeleteListener, OnProductEditListener {

    private ProductViewModel appVendasProdutosCrudViewModel;
    private CRUDProductListRVAdapter adapter;
    private ActionMode mActionMode;
    private long mLastClickTime = 0;
    private static final int ADD_PRODUCT_RESULT_CODE = 1000;
    private static final int EDIT_PRODUCT_RESULT_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_vendas_product_crud);

        getSupportActionBar().setTitle("Produtos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.app_vendas_back_icon);

        FloatingActionButton addProductFAB = findViewById(R.id.appVendasProductCrudFAB);
        addProductFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                Intent intent = new Intent(AppVendasProductDetailsCrud.this, AppVendasAddProducts.class);
                startActivityForResult(intent, ADD_PRODUCT_RESULT_CODE);

            }
        });

        RecyclerView appVendasProdutosCrudRecyclerView = findViewById(R.id.productCrudRecyclerView);
        adapter = new CRUDProductListRVAdapter(this, this, this);

        appVendasProdutosCrudRecyclerView.setAdapter(adapter);
        appVendasProdutosCrudRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        appVendasProdutosCrudViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        appVendasProdutosCrudViewModel.getAvailableProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter.setProducts(products);
            }
        });
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.app_vendas_contextual_actionbar, menu);
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPurpleDark));

            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            actionMode.setTitle("Excluir");

            return false;
        }

        @Override
        public boolean onActionItemClicked(final ActionMode actionMode, MenuItem menuItem) {

            switch (menuItem.getItemId()) {
                case R.id.delete_icon:
                    new MaterialAlertDialogBuilder(AppVendasProductDetailsCrud.this, R.style.Theme_MaterialComponents_Light_Dialog)
                            .setTitle("Deletar produtos?")
                            .setMessage("Ao deletar os produtos eles não poderão mais ser acessados. \nDeseja mesmo fazer isso?")
                            .setPositiveButton("Aceitar", /* listener = */ new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    List<Product> productList = new ArrayList<>();
                                    productList = adapter.selectedItemsRecyclerView();

                                    if (productList != null && productList.size() > 0) {
                                        for (Product product : productList) {
                                            appVendasProdutosCrudViewModel.toggleProductAvailability(product);
                                        }
                                        adapter.notifyDataSetChanged();
                                    }

                                    actionMode.finish();
                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            }).show();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            mActionMode = null;
            adapter.deselectRecyclerView();
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case RESULT_OK:
                switch (requestCode) {
                    case ADD_PRODUCT_RESULT_CODE:
                        Toast.makeText(this, "Produto adicionado", Toast.LENGTH_SHORT).show();
                        break;

                    case EDIT_PRODUCT_RESULT_CODE:
                        Toast.makeText(this, "Produto alterado", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }
                break;

            case RESULT_CANCELED:
                if (data != null && data.getStringExtra("erro") != null)
                    Toast.makeText(this, data.getStringExtra("erro"), Toast.LENGTH_SHORT).show();
            default:
                break;
        }
    }

    @Override
    public boolean deleteProduct(final Product product) {
        adapter.toggleProduct(product);
        if (!adapter.isRecyclerViewSelected()) {
            mActionMode.finish();
            return false;
        }
        if (mActionMode != null) {
            return true;
        }

        mActionMode = startActionMode(mActionModeCallback);

        return true;
    }

    @Override
    public void editProduct(final Product product) {
        if (mActionMode != null) {
            adapter.toggleProduct(product);
            if (!adapter.isRecyclerViewSelected()) {
                mActionMode.finish();
            }
        } else {
            Intent intent = new Intent(this, AppVendasProductEdit.class);
            intent.putExtra("productName", product.getProductName());
            intent.putExtra("productDescription", product.getProductDescrition());
            intent.putExtra("productId", product.getId());
            intent.putExtra("productPrice", product.getProductPrice());
            intent.putExtra("productGroup", product.getProductGroup());
            intent.putExtra("productOnSale", product.getOnSaleProduct());

            startActivityForResult(intent, EDIT_PRODUCT_RESULT_CODE);
        }
    }

}
