package fpt.edu.vn.koreanbookstore.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fpt.edu.vn.koreanbookstore.Adapter.BannerAdapter;
import fpt.edu.vn.koreanbookstore.Adapter.BookAdapter;
import fpt.edu.vn.koreanbookstore.BookCategory;
import fpt.edu.vn.koreanbookstore.R;
import fpt.edu.vn.koreanbookstore.book.Book;
import fpt.edu.vn.koreanbookstore.chat.Chat;

public class UserHome extends Fragment {
    private ViewPager2 bannerViewPager;
    private Handler bannerHandler;
    private Runnable bannerRunnable;
    private int currentBannerIndex = 0;
    private RecyclerView popularBooksRecycler, parentingsRecycler, comicsRecycler, novelsRecycler, rvSearchResults;
    private ImageView btnChat;
    private List<Book> allBooks;
    private EditText etSearch;
    private TextView tvNotFound;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);

        etSearch = view.findViewById(R.id.et_search);
        rvSearchResults = view.findViewById(R.id.rv_search_results);
        tvNotFound = view.findViewById(R.id.tv_not_found);


        LinearLayout categoryNovels = view.findViewById(R.id.category_novels);
        LinearLayout categoryComics = view.findViewById(R.id.category_comics);
        LinearLayout categoryParentings = view.findViewById(R.id.category_parentings);
        LinearLayout categoryLanguages = view.findViewById(R.id.category_languages);
        LinearLayout categoryChilds = view.findViewById(R.id.category_childs);
        LinearLayout categoryUnibook = view.findViewById(R.id.category_unibook);
        LinearLayout categoryOld = view.findViewById(R.id.category_old);

        categoryNovels.setOnClickListener(v -> openCategoryActivity("novel", "Tiểu thuyết"));
        categoryComics.setOnClickListener(v -> openCategoryActivity("comic", "Truyện tranh"));
        categoryParentings.setOnClickListener(v -> openCategoryActivity("parenting", "Giáo dục"));
        categoryLanguages.setOnClickListener(v -> openCategoryActivity("language", "Ngoại ngữ"));
        categoryChilds.setOnClickListener(v -> openCategoryActivity("children", "Nhi đồng"));
        categoryUnibook.setOnClickListener(v -> openCategoryActivity("unibook", "Chuyên ngành"));
        categoryOld.setOnClickListener(v -> openCategoryActivity("old", "Sách cũ"));

        btnChat = view.findViewById(R.id.btn_chat);
        btnChat.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), Chat.class);
            startActivity(intent);
        });

        bannerViewPager = view.findViewById(R.id.banner_view_pager);

        List<Integer> banners = Arrays.asList(
                R.drawable.banner1,
                R.drawable.banner2,
                R.drawable.banner3
        );

        BannerAdapter adapter = new BannerAdapter(banners);
        bannerViewPager.setAdapter(adapter);

        bannerHandler = new Handler(Looper.getMainLooper());
        bannerRunnable = new Runnable() {
            @Override
            public void run() {
                currentBannerIndex = (currentBannerIndex + 1) % banners.size();
                bannerViewPager.setCurrentItem(currentBannerIndex, true);
                bannerHandler.postDelayed(this, 3000);
            }
        };
        bannerHandler.postDelayed(bannerRunnable, 3000);

        popularBooksRecycler = view.findViewById(R.id.rv_popular_products);
        allBooks = Arrays.asList(
                new Book(R.drawable.comic1, "드래곤볼 애니메이션 가이드", 780000, "Truyện kể về các nhân vật, cốt truyện trong anime Dragon Ball", "co01", "Shueisha", "comic"),
                new Book(R.drawable.comic2, "스파이 패밀리 15", 100000, "Quan hệ giữa các thành viên nhà Forger", "co02", "Tatsuya Endo", "comic"),
                new Book(R.drawable.comic3, "위치 워치 18", 112000, "Phù thủy Nico và vệ sĩ Morihito", "co03", "Shinohara Kenta", "comic"),
                new Book(R.drawable.comic4, "바람계곡의 나우시카 세트", 1128000, "Công chúa thung lũng gió Nausicaä", "co04", "Hayao Miyazaki", "comic"),
                new Book(R.drawable.comic5, "순정빌런 11", 4180000, "Tình cảm và trưởng thành của nhân vật", "co05", "김캔디", "comic"),
                new Book(R.drawable.comic7, "드래곤볼 완전판 세트", 220000, "Dragon Ball trọn bộ từ nhỏ đến trưởng thành", "co07", "Akira Toriyama", "comic"),

                new Book(R.drawable.novel1, "혼모노", 462000, "Truyện ngắn/phóng sự xã hội phản ánh hiện trạng gia đình", "n01", "성해나", "novel"),
                new Book(R.drawable.novel2, "디 에센셜", 300000, "Tuyển tập những tác phẩm tiêu biểu của Han Kang", "n02", "한강", "novel"),
                new Book(R.drawable.novel3, "채식주의자", 370000, "Người phụ nữ quyết định ngừng ăn thịt sau một giấc mơ kỳ lạ", "n03", "한강", "novel"),
                new Book(R.drawable.novel4, "노랑무늬영원", 260000, "Khu vườn nở đầy hoa thủy tiên vàng", "n04", "정보라", "novel"),
                new Book(R.drawable.novel5, "바람이 분다 가라", 250000, "Tiểu thuyết thơ về sự ra đi và chia tay", "n05", "강영숙", "novel"),
                new Book(R.drawable.novel6, "검은 사슴", 280000, "Người phụ nữ bị vết thương tâm lý sâu sắc ám ảnh", "n06", "한강", "novel"),

                new Book(R.drawable.parent1, "비폭력대화", 450000, "Giao tiếp tích cực với trẻ", "p01", "정채현", "parenting"),
                new Book(R.drawable.parent2, "인정 육아", 340000, "Dạy con bằng tình yêu", "p02", "김유라", "parenting"),
                new Book(R.drawable.parent3, "성적을 부탁해 티처스", 400000, "Dành cho giáo viên và phụ huynh", "p03", "이승기 외", "parenting"),
                new Book(R.drawable.parent4, "중독되는 아이들", 320000, "Nói về nghiện thiết bị số", "p04", "정혜신", "parenting"),
                new Book(R.drawable.parent5, "엄마, 나는 자라고 있어요", 340000, "Phát triển trẻ nhỏ, đồng hành cùng con", "p05", "김영훈", "parenting"),
                new Book(R.drawable.parent7, "삐뽀삐뽀 119 소아과", 570000, "Chi tiết bệnh trẻ em, cách sơ cứu", "p07", "하정훈", "parenting"),

                new Book(R.drawable.languages1, "Day‑by‑Day Writing Korean Hangeul", 370000, "Thực hành viết chữ Hangeul từng ngày", "l01", "The Calling", "language"),
                new Book(R.drawable.unibook1, "모두를 위한 최신 ChatGPT 프롬프트 엔지니어링", 420000, "Hướng dẫn sử dụng ChatGPT, prompt engineering", "u01", "MASO CAMPUS", "unibook"),
                new Book(R.drawable.child1, "티니핑이 된 로이", 578000, "Câu chuyện về Roy biến thành 티니핑", "ch01", "아이휴먼", "children")
        );
        setupRecycler(popularBooksRecycler, allBooks);

        // Tiểu thuyết
        novelsRecycler = view.findViewById(R.id.rv_novels);
        List<Book> novels = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getCategoryId().equals("novel")) novels.add(book);
        }
        setupRecycler(novelsRecycler, novels);

        // Giáo dục
        parentingsRecycler = view.findViewById(R.id.rv_parentings);
        List<Book> parentings = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getCategoryId().equals("parenting")) parentings.add(book);
        }
        setupRecycler(parentingsRecycler, parentings);

        // Truyện tranh
        comicsRecycler = view.findViewById(R.id.rv_comics);
        List<Book> comics = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getCategoryId().equals("comic")) comics.add(book);
        }
        setupRecycler(comicsRecycler, comics);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String keyword = s.toString().toLowerCase().trim();

                // Ẩn các phần khác khi đang tìm
                popularBooksRecycler.setVisibility(View.GONE);
                parentingsRecycler.setVisibility(View.GONE);
                comicsRecycler.setVisibility(View.GONE);
                novelsRecycler.setVisibility(View.GONE);

                if (keyword.isEmpty()) {
                    rvSearchResults.setVisibility(View.GONE);
                    tvNotFound.setVisibility(View.GONE);
                    popularBooksRecycler.setVisibility(View.VISIBLE);
                    parentingsRecycler.setVisibility(View.VISIBLE);
                    comicsRecycler.setVisibility(View.VISIBLE);
                    novelsRecycler.setVisibility(View.VISIBLE);
                    return;
                }

                List<Book> filtered = new ArrayList<>();
                for (Book book : allBooks) {
                    if (book.getTitle().toLowerCase().contains(keyword) ||
                            book.getAuthor().toLowerCase().contains(keyword)) {
                        filtered.add(book);
                    }
                }

                if (filtered.isEmpty()) {
                    rvSearchResults.setVisibility(View.GONE);
                    tvNotFound.setVisibility(View.VISIBLE);
                } else {
                    rvSearchResults.setVisibility(View.VISIBLE);
                    tvNotFound.setVisibility(View.GONE);
                    setupRecycler(rvSearchResults, filtered);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


        return view;
    }
    private void setupRecycler(RecyclerView recyclerView, List<Book> books) {
        BookAdapter adapter = new BookAdapter(requireContext(), books);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bannerHandler.removeCallbacks(bannerRunnable);
    }
    private void openCategoryActivity(String categoryId, String title) {
        Intent intent = new Intent(requireContext(), BookCategory.class);
        intent.putExtra("categoryId", categoryId);
        intent.putExtra("title", title);

        BookCategory.allBooks = allBooks;
        startActivity(intent);
    }
}
