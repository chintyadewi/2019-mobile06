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
import android.widget.Toast;

import id.ac.polinema.idealbodyweight.R;
import id.ac.polinema.idealbodyweight.util.BodyMassIndex;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BodyMassIndexFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class BodyMassIndexFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public BodyMassIndexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_body_mass_index, container, false);
        final EditText weightText = view.findViewById(R.id.edt_weight);
        final EditText heightText  = view.findViewById(R.id.edt_height);

        Button calculateButton = view.findViewById(R.id.btn_calculate);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    String weightString = weightText.getText().toString();
                    String heightString = heightText.getText().toString();
                    if (!TextUtils.isEmpty(weightString) && !TextUtils.isEmpty(heightString)) {
                        int weight = Integer.parseInt(weightString);
                        int height = Integer.parseInt(heightString);
                        BodyMassIndex bmi=new BodyMassIndex(weight, height);
                        mListener.onCalculateBodyMassIndexClicked(bmi.getIndex());
                    } else {
                        Toast.makeText(getActivity(), "Please input your weight and height", Toast.LENGTH_SHORT).show();
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
        void onCalculateBodyMassIndexClicked(float index);
    }
}
