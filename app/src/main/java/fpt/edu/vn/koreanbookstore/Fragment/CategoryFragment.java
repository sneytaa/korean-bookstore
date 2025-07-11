package fpt.edu.vn.koreanbookstore.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.vn.koreanbookstore.Adapter.CategoryAdapter;
import fpt.edu.vn.koreanbookstore.Category;
import fpt.edu.vn.koreanbookstore.R;

public class CategoryFragment extends Fragment {

    private RecyclerView recyclerView;

    public CategoryFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = view.findViewById(R.id.recyclerCategory);

        List<Category> categories = new ArrayList<>();
        categories.add(new Category("novel", "Tiểu thuyết"));
        categories.add(new Category("comic", "Truyện tranh"));
        categories.add(new Category("parenting", "Giáo dục"));
        categories.add(new Category("language", "Ngoại ngữ"));
        categories.add(new Category("children", "Nhi đồng"));
        categories.add(new Category("unibook", "Chuyên ngành"));
        categories.add(new Category("old", "Sách cũ"));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new CategoryAdapter(getContext(), categories));

        return view;
    }
}
