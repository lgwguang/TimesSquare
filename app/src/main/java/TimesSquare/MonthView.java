// Copyright 2012 Square, Inc.
package TimesSquare;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.up.mytimessquare.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class MonthView extends LinearLayout {
  TextView title;
  CalendarGridView grid;
  private Listener listener;
  public static String[] tag=new String[2];

  public static MonthView create(ViewGroup parent, LayoutInflater inflater,
                                 DateFormat weekdayNameFormat, Listener listener, Calendar today, int dividerColor,
                                 int dayBackgroundResId, int dayTextColorResId, int titleTextColor, int headerTextColor) {
    final MonthView view = (MonthView) inflater.inflate(R.layout.month, parent, false);
    view.setDividerColor(dividerColor);
    view.setDayTextColor(dayTextColorResId);
    view.setTitleTextColor(titleTextColor);
    view.setHeaderTextColor(headerTextColor);

    if (dayBackgroundResId != 0) {
      view.setDayBackground(dayBackgroundResId);
    }

    final int originalDayOfWeek = today.get(Calendar.DAY_OF_WEEK);

    int firstDayOfWeek = today.getFirstDayOfWeek();
    final CalendarRowView headerRow = (CalendarRowView) view.grid.getChildAt(0);
    for (int offset = 0; offset < 7; offset++) {
      today.set(Calendar.DAY_OF_WEEK, firstDayOfWeek + offset);
      final TextView textView = (TextView) headerRow.getChildAt(offset);
      textView.setText(weekdayNameFormat.format(today.getTime()).substring(1));
    }
    today.set(Calendar.DAY_OF_WEEK, originalDayOfWeek);
    view.listener = listener;
    return view;
  }

  public MonthView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    title = (TextView) findViewById(R.id.title);
    grid = (CalendarGridView) findViewById(R.id.calendar_grid);
  }

  public void init(MonthDescriptor month, List<List<MonthCellDescriptor>> cells,
                   boolean displayOnly) {
    Logr.d("Initializing MonthView (%d) for %s", System.identityHashCode(this), month);
    long start = System.currentTimeMillis();
    title.setText(month.getLabel());

    final int numRows = cells.size();
    grid.setNumRows(numRows);
    for (int i = 0; i < 6; i++) {
      CalendarRowView weekRow = (CalendarRowView) grid.getChildAt(i + 1);
      weekRow.setListener(listener);
      if (i < numRows) {
        weekRow.setVisibility(VISIBLE);
        List<MonthCellDescriptor> week = cells.get(i);
        for (int c = 0; c < week.size(); c++) {
          MonthCellDescriptor cell = week.get(c);
          CalendarCellView cellView = (CalendarCellView) weekRow.getChildAt(c);

          cellView.setText(Integer.toString(cell.getValue()));
          cellView.setEnabled(cell.isCurrentMonth());
          cellView.setClickable(!displayOnly);

          cellView.setSelectable(cell.isSelectable());
          cellView.setSelected(cell.isSelected());
          if(cell.isSelected()&&cell.isStart())
          {  cellView.setText(Integer.toString(cell.getValue())+"\n"+tag[0]);
             cellView.setGravity(Gravity.CENTER);
             cell.setStart(false);
          }
          if(cell.isSelected()&&cell.isEnd())
          {
        	  cellView.setText(Integer.toString(cell.getValue())+"\n"+tag[1]);
              cellView.setGravity(Gravity.CENTER);
              cell.setEnd(false);
          } 
           
          cellView.setCurrentMonth(cell.isCurrentMonth());
          cellView.setToday(cell.isToday());
//          if(cell.isToday())
//        	  cellView.setText("今天");
          cellView.setRangeState(cell.getRangeState());
          cellView.setHighlighted(cell.isHighlighted());
          cellView.setTag(cell);
        }
      } else {
        weekRow.setVisibility(GONE);
      }
    }
    Logr.d("MonthView. took %d ms", System.currentTimeMillis() - start);
  }

  public void setDividerColor(int color) {
    grid.setDividerColor(color);
  }

  public void setDayBackground(int resId) {
    grid.setDayBackground(resId);
  }

  public void setDayTextColor(int resId) {
    grid.setDayTextColor(resId);
  }

  public void setTitleTextColor(int color) {
    title.setTextColor(color);
  }

  public void setHeaderTextColor(int color) {
    grid.setHeaderTextColor(color);
  }

  public interface Listener {
    void handleClick(MonthCellDescriptor cell);
  }
}
