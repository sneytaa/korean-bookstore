package fpt.edu.vn.koreanbookstore.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.List;

import fpt.edu.vn.koreanbookstore.Adapter.BannerAdapter;
import fpt.edu.vn.koreanbookstore.Adapter.BookAdapter;
import fpt.edu.vn.koreanbookstore.book.Book;
import fpt.edu.vn.koreanbookstore.R;
import fpt.edu.vn.koreanbookstore.chat.Chat;

public class UserHome extends Fragment {
    private ViewPager2 bannerViewPager;
    private Handler bannerHandler;
    private Runnable bannerRunnable;
    private int currentBannerIndex = 0;
    private RecyclerView popularBooksRecycler, parentingsRecycler, comicsRecycler, novelsRecycler;
    private ImageView btnChat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);

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

        //sách top trong tuần
        popularBooksRecycler = view.findViewById(R.id.rv_popular_products);

        List<Book> popularBooks = Arrays.asList(
                new Book(R.drawable.comic1, "드래곤볼 애니메이션 가이드", 780000, "Truyện kể về các nhân vật, cốt truyện trong anime Dragon Bal", "co01", "Shueisha"),
                new Book(R.drawable.parent1, "비폭력대화", 450000, "Bí quyết giao tiếp tích cực với trẻ", "p01", "정채현"),
                new Book(R.drawable.languages1, "Day‑by‑Day Writing Korean Hangeul", 370000, "Thực hành viết chữ Hangeul theo từng ngày, dành cho trẻ em", "l01", "The Calling"),
                new Book(R.drawable.child1, "티니핑이 된 로이", 578000, "Câu chuyện về Roy biến thành “티니핑”", "ch01", "아이휴먼"),
                new Book(R.drawable.novel1, "혼모노", 462000, "Tập truyện ngắn/phóng sự xã hội phản ánh hiện trạng gia đình, người mẹ đơn thân", "n01", "성해나"),
                new Book(R.drawable.unibook1, "모두를 위한 최신 ChatGPT 프롬프트 엔지니어링", 420000, "Hướng dẫn sử dụng ChatGPT, prompt engineering", "u01", "MASO CAMPUS")
        );

        BookAdapter popularAdapter  = new BookAdapter(requireContext(), popularBooks);
        popularBooksRecycler.setAdapter(popularAdapter );
        popularBooksRecycler.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );

        //sách tiểu thuyết
        novelsRecycler = view.findViewById(R.id.rv_novels);

        List<Book> novels = Arrays.asList(
                new Book(R.drawable.novel1, "혼모노", 462000, "ập truyện ngắn/phóng sự xã hội phản ánh hiện trạng gia đình, người mẹ đơn thân", "n01", "성해나"),
                new Book(R.drawable.novel2, "디 에센셜", 300000, "Đây là tuyển tập những tác phẩm tiêu biểu của nhà văn Han Kang", "n02", "한강"),
                new Book(R.drawable.novel3, "채식주의자", 370000, "Một người phụ nữ Hàn Quốc quyết định ngừng ăn thịt sau một giấc mơ kỳ lạ", "n03", "한강"),
                new Book(R.drawable.novel4, "노랑무늬영원", 260000, "Một khu vườn kỳ lạ nở đầy hoa thủy tiên vàng là biểu tượng cho ký ức, cái chết và sự cứu chuộc", "n04", "정보라"),
                new Book(R.drawable.novel5, "바람이 분다 가라", 250000, "Một tiểu thuyết đầy chất thơ về sự ra đi và chia tay.", "n05", "강영숙"),
                new Book(R.drawable.novel6, "검은 사슴", 280000, "Kể về một người phụ nữ với vết thương tâm lý sâu sắc, bị ám ảnh", "n06", "한강")
        );

        BookAdapter novelsAdapter  = new BookAdapter(requireContext(), novels);
        novelsRecycler.setAdapter(novelsAdapter );
        novelsRecycler.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );

        //sách giáo dục
        parentingsRecycler = view.findViewById(R.id.rv_parentings);

        List<Book> parentings = Arrays.asList(
                new Book(R.drawable.parent2, "인정 육아", 340000, "Giao tiếp tích cực, đồng cảm với con cái, dạy con bằng tình yêu.", "p02", "김유라"),
                new Book(R.drawable.parent1, "비폭력대화", 450000, "Bí quyết giao tiếp tích cực với trẻ", "p01", "정채현"),
                new Book(R.drawable.parent3, "성적을 부탁해 티처스", 400000, " Đây là cuốn sách dành cho giáo viên và phụ huynh", "p03", "이승기 외"),
                new Book(R.drawable.parent4, "중독되는 아이들", 320000, ": Cuốn sách nói về hiện tượng nghiện thiết bị số như điện thoại, game, MXH", "p04", "정혜신"),
                new Book(R.drawable.parent5, "엄마, 나는 자라고 있어요", 340000, "Phát triển trẻ nhỏ, đồng hành cùng con.", "p05", "김영훈"),
                new Book(R.drawable.parent7, "삐뽀삐뽀 119 소아과", 570000, "Cung cấp thông tin cực kỳ chi tiết về bệnh thường gặp ở trẻ em, cách sơ cứu", "p07", "하정훈")
        );

        BookAdapter parentingsAdapter  = new BookAdapter(requireContext(), parentings);
        parentingsRecycler.setAdapter(parentingsAdapter );
        parentingsRecycler.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );

        //sách truyện
        comicsRecycler = view.findViewById(R.id.rv_comics);

        List<Book> comics = Arrays.asList(
                new Book(R.drawable.comic1, "드래곤볼 애니메이션 가이드", 780000, "Truyện kể về các nhân vật, cốt truyện trong anime Dragon Bal", "co01", "Shueisha"),
                new Book(R.drawable.comic2, "스파이 패밀리 15", 100000, "Trong phần này, mối quan hệ giữa các thành viên nhà Forger càng trở nên sâu sắc", "co02", "Tatsuya Endo"),
                new Book(R.drawable.comic3, "위치 워치 18", 112000, "Câu chuyện về phù thủy Nico và vệ sĩ đồng hành Morihito", "co03", "Shinohara Kenta"),
                new Book(R.drawable.comic4, "바람계곡의 나우시카 세트", 1128000, "Kể về hành trình của Nausicaä – công chúa của thung lũng gió, một cô gái yêu thiên nhiên", "co04", "Hayao Miyazaki"),
                new Book(R.drawable.comic5, "순정빌런 11", 4180000, "Tập 11 kết thúc với cao trào tình cảm và sự trưởng thành của các nhân vật", "co05", "김캔디"),
                new Book(R.drawable.comic7, "드래곤볼 완전판 세트", 220000, "Trọn bộ Dragon Ball từ đầu đến cuối, kể về hành trình của Son Goku từ lúc nhỏ đến khi trưởng thành", "co07", "Akira Toriyama")
        );

        BookAdapter comicsAdapter  = new BookAdapter(requireContext(), comics);
        comicsRecycler.setAdapter(comicsAdapter );
        comicsRecycler.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bannerHandler.removeCallbacks(bannerRunnable);
    }
}