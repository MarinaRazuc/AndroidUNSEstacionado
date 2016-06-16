package fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.estacionado.R;
import com.google.android.gms.maps.model.LatLng;

public class botones extends Fragment {
    private View v;
    private OnFragmentInteractionListener mListener;

    public botones() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static botones newInstance(String param1, String param2) {
        botones fragment = new botones();
        Bundle args = new Bundle();
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_blank, container, false);

        v.findViewById(R.id.mostrarPos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarPosicionFragment();
            }
        });
        v.findViewById(R.id.nuevaPos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NuevaPosicionFragment();
            }
        });

        return v;

    }

    public void mostrarPosicionFragment(){
        if (mListener != null) {
            mListener.mostrarPosicion();
        }
    }
    public void NuevaPosicionFragment(){
        if (mListener != null) {
            mListener.NuevaPosicion();
        }
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void mostrarPosicion();
        void NuevaPosicion();
    }
}
