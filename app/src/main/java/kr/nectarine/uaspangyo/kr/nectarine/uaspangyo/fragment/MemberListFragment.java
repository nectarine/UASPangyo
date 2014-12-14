package kr.nectarine.uaspangyo.kr.nectarine.uaspangyo.fragment;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.ButterKnife;
import butterknife.InjectView;
import kr.nectarine.uaspangyo.R;
import kr.nectarine.uaspangyo.kr.nectarine.uaspangyo.model.Member;
import kr.nectarine.uaspangyo.kr.nectarine.uaspangyo.widget.MemberListAdapter;

/**
 * Created by Nectarine on 2014-12-14.
 */
public class MemberListFragment extends Fragment {

    @InjectView(R.id.lv_member)
    ListView lvMember;

    MemberListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new MemberListAsyncTask().execute();

    }

    class MemberListAsyncTask extends AsyncTask<Void, Void, Member[]> {

        private static final String URL_MEMBER_LIST = "https://raw.githubusercontent.com/nectarine/UASPangyo/master/app/extra/member.json";

        @Override
        protected Member[] doInBackground(Void... params) {
            String body = HttpRequest.get(URL_MEMBER_LIST).contentType("application/json").body();
            return new Gson().fromJson(body, new TypeToken<Member[]>(){}.getType());
        }

        @Override
        protected void onPostExecute(Member[] members) {
            super.onPostExecute(members);
            adapter = new MemberListAdapter(getActivity(), R.layout.list_item_member,members);
            lvMember.setAdapter(adapter);
        }
    }

}
