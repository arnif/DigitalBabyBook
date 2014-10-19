package is.ru.DigitalBabyBook.Activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import is.ru.DigitalBabyBook.Global;
import is.ru.DigitalBabyBook.R;
import is.ru.DigitalBabyBook.domain.BirthdayEvent;

import java.util.Calendar;

/**
 * Created by Tommz on 18.10.2014.
 */
public class CreateHoliday extends Activity {


    private BirthdayEvent event;
    private Global global = Global.getInstance();
    //Might need an adapter

    private ImageView pickDate;
    private TextView dateDisplay;
    private int mYear;
    private int mMonth;
    private int mDay;

    static final int DATE_DIALOG_ID = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //remove top bar
        setContentView(R.layout.add_holiday);

        dateDisplay = (TextView) this.findViewById(R.id.holiday_dateDisplay);
        pickDate = (ImageView) this.findViewById(R.id.holiday_datePicker);


        pickDate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                showDialog(DATE_DIALOG_ID);
            }
        });

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        updateDisplay();

    }
    public void addHoliday(View view) {
        TextView eventDescription = (TextView) this.findViewById(R.id.eventDescription);
        TextView dateOfHoliday = (TextView) this.findViewById(R.id.dateDisplay);
        TextView location = (TextView) this.findViewById(R.id.location);
        TextView photos = (TextView) this.findViewById(R.id.photos);
        TextView gifts = (TextView) this.findViewById(R.id.gifts);
//        TextView baby = (TextView) this.findViewById(R.id.baby); ???

//        event = new BirthdayEvent(
//                eventDescription.toString(),
//                dateOfHoliday.toString(),
//                location.toString(),
//                photos.toString(),
//                gifts.toString(),
//                global.selectedBaby
//        );
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }

    private void updateDisplay() {
        dateDisplay.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mMonth + 1).append("-")
                        .append(mDay).append("-")
                        .append(mYear).append(" "));
    }
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
            };

}