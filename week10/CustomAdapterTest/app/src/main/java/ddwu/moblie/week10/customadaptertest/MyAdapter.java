package ddwu.moblie.week10.customadaptertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {   //BaseAdapter 상속받음
    private Context context;
    private int layout;
    private ArrayList<MyData> myDataList;
    private LayoutInflater layoutInflater;

    public MyAdapter(Context context, int layout, ArrayList<MyData> myDataList) {
        this.context = context;
        this.layout = layout;
        this.myDataList = myDataList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  //getSystemService 쓰려면 context 필요. Activity클래스가 아니고 MyAdapter클래스라서 getSystemService를 가지고 있지 않음. 따라서 매게변수로 전달받은 context가 가지고 있는 메소드라서 context.을 붙이는것!
    }

    //필수 메소드들 (아래 4개) (추상클래스라서 상속받은 메소드들 필수로 생성해야함)
    @Override
    public int getCount() {
        return myDataList.size();
    }  //전체 원본 데이터의 개수 반환

    @Override
    public Object getItem(int i) {
        return myDataList.get(i);
    }  //특정 위치의 데이터 값 반환. Object이기에 어떤 타입도 반환 가능. 원본을 가지고 있는 MyData에서 i번째 데이터를 반환. (DB일 경우는 get이 아닌 record)

    @Override
    public long getItemId(int i) {   //내부적으로 long타입임. 따라서 우리도 long타입으로 식별하는 것을 따라야함.
        return myDataList.get(i).get_id();    //get_id() -> _id의 getter
    }  //특정 위치의 데이터 항목 아이디 반환

    @Override   //원본 데이터의 개수만큼 반복 호출 (pos가 몇번째 View를 요청하는지 나타내는 변수. pos에 해당하는 데이터 가져옴)
    public View getView(int pos, View convertView, ViewGroup viewGroup) { //convertview-> 보여질 화면. 만들어져서 반환할 화면. (전달받은 뷰) 맨 처음엔 뷰를 만든 적 없기에 빈(껍데기) 뷰가 들어옴. 여기에 레이아웃으로 화면을 만들어서 데이터를 채워넣어 리턴시켜야함
        final int position = pos;   //함수 종료후 매개변수 없어지므로 상수로 만들어줌. ex) 나중에 버튼 눌렀을때 위치 확인 못하기에 필요

        //스크롤 개선 위함
        ViewHolder holder;

        //view가 비어있는지 아닌지 검사. 비어있을 경우 inflater를 사용해서 layout에 해당하는 view 객체 생성 (맨 처음에 null이 들어오기 때문에 실제 화면을 만드는 것)
        if(convertView == null) {
            convertView = layoutInflater.inflate(layout, viewGroup, false);  //새로 레이아웃 만듦.(숫자, 이름, 전화번호, 버튼이 들어있는)

        //2) 슬라이드 p.16
        //스크롤 개선 위함
            holder = new ViewHolder();
            holder.textNo = convertView.findViewById(R.id.tvNo);
            holder.textName = convertView.findViewById(R.id.tvName);
            holder.textPhone = convertView.findViewById(R.id.tvPhone);
            holder.btnCheck = convertView.findViewById(R.id.btnCheck);
            convertView.setTag(holder);  //setTag에 보관
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textNo.setText(String.valueOf(myDataList.get(position).get_id()));  //꼭 string으로 변환!(String.valueOf) 숫자 그대로 들어가면 string으로 된 ID를 인식하지 못함. 매개변수 pos 포지션위치에 해당하는 항목을 꺼내서 그 아이디와 화면 결합시킴
        holder.textName.setText(myDataList.get(position).getName());
        holder.textPhone.setText(myDataList.get(position).getPhone());
        holder.btnCheck.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(context, myDataList.get(position).getPhone() + " 선택", Toast.LENGTH_SHORT).show();
            }
        });

        //1)
        //스크롤 개선 전(
//        //layout만든 화면이 convertView이기에 이 안에 각각의 항목들이 들어있음.
//        //값을 채워넣는 작업.  그냥 findViewById쓰면 안되고 위의 이유로 인해 convertView 안에서 값을 찾는것!!
//        TextView tvNo = convertView.findViewById(R.id.tvNo);
//        TextView tvName = convertView.findViewById(R.id.tvName);
//        TextView tvPhone = convertView.findViewById(R.id.tvPhone);
//        Button btnCheck = convertView.findViewById(R.id.btnCheck);
//        btnCheck.setFocusable(false);   //button에만 포커싱! 없으면 클릭 자체를 button이 인식하기 때문에 다른게 동작하지 않음
//
//        //실제 데이터 결합
//        tvNo.setText(String.valueOf(myDataList.get(position).get_id()));  //꼭 string으로 변환!(String.valueOf) 숫자 그대로 들어가면 string으로 된 ID를 인식하지 못함. 매개변수 pos 포지션위치에 해당하는 항목을 꺼내서 그 아이디와 화면 결합시킴
//        tvName.setText(myDataList.get(position).getName());
//        tvPhone.setText(myDataList.get(position).getPhone());

        //각 항목에도 이벤트 리스너 설정할 수 있음. ex) 홍길동 글자 터치 리스너
//        tvName.setOnTouchListener(new View.OnTouchListener() {
//           @Override
//           public boolean onTouch(View view, MotionEvent motionEvent) {
//               Toast.makeText(context, "Touch", Toast.LENGTH_SHORT).show();
//               return true;
//           }
//        });
        //
//        btnCheck.setOnClickListener(new Button.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, myDataList.get(position).getPhone() + " 선택", Toast.LENGTH_SHORT).show();
//            }
//        });
// )
        return convertView;  //만든 객체 view 반환
    }

    //스크롤 속도 개선위함. 이제 이걸 사용!!
    static class ViewHolder{
        TextView textNo;
        TextView textName;
        TextView textPhone;
        Button btnCheck;
    }
}
