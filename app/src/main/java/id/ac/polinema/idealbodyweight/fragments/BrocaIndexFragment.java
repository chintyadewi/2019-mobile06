package id.ac.polinema.idealbodyweight.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import id.ac.polinema.idealbodyweight.R;
import id.ac.polinema.idealbodyweight.util.BrocaIndex;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BrocaIndexFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class BrocaIndexFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public BrocaIndexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_broca_index, container, false);
        final RadioGroup genderGroup = view.findViewById(R.id.rg_gender);
        final EditText heightText  = view.findViewById(R.id.edt_height);

        Button calculateButton = view.findViewById(R.id.btn_calculate);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    String heightString = heightText.getText().toString();
                    int checkedId = genderGroup.getCheckedRadioButtonId();
                    if ((checkedId != -1) && !TextUtils.isEmpty(heightString)) {
                        int height = Integer.parseInt(heightString);
                        int gender = (checkedId == R.id.rb_male) ? BrocaIndex.MALE : BrocaIndex.FEMALE;
                        BrocaIndex brocaIndex = new BrocaIndex(gender, height);
                        mListener.onCalculateBrocaIndexClicked(brocaIndex.getIndex());
                    } else {
                        Toast.makeText(getActivity(), "Please select gender and input your height", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onCalculateBrocaIndexClicked(float index);
    }
}
