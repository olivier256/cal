package cal;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cal {

	private static final String EMPTY_DAYS = "                     ";

	public static void main(String[] args) {
		if (args.length == 1) {
			cal(Integer.parseInt(args[0]));
		} else if (args.length == 2) {
			List<String> calList = calList(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
			for (String string : calList) {
				System.out.println(string);
			}
		} else {
			System.err.println("Usage: cal year [month]");
		}
	}

	private static List<String> calList(int year, int month) {
		List<String> strings = new ArrayList<>();
		strings.add("Lu Ma Me Je Ve Sa Di");

		LocalDate date = LocalDate.now().withYear(year).withMonth(month).withDayOfMonth(1);
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		StringBuilder sb = new StringBuilder();
		for (int pad = 1; pad < dayOfWeek.getValue(); pad++) {
			sb.append("   ");
		}
		for (int _dayOfWeek = dayOfWeek.getValue(); _dayOfWeek <= 7; _dayOfWeek++) {
			sb.append(" " + date.getDayOfMonth() + " ");
			date = date.plusDays(1);
		}
		strings.add(sb.toString());

		sb.setLength(0);
		while (date.getMonth().getValue() == month) {
			String dayOfMonth = date.getDayOfMonth() < 10 ? " " : "";
			dayOfMonth += date.getDayOfMonth() + " ";
			sb.append(dayOfMonth);
			date = date.plusDays(1);
			if (date.getDayOfWeek() == DayOfWeek.MONDAY) {
				strings.add(sb.toString());
				sb.setLength(0);
			}
		}
		if (sb.length() > 0) {
			for (int i = sb.length(); i < 21; i++) {
				sb.append(" ");
			}
			strings.add(sb.toString());
		}
		return strings;
	}

	private static void cal(int year) {
		for (int monthRow = 0; monthRow < 4; monthRow++) {
			int month = monthRow * 3 + 1;
			List<String> calList = calList(year, month);
			List<String> calList1 = calList(year, month + 1);
			List<String> calList2 = calList(year, month + 2);
			int max = Math.max((Math.max(calList.size(), calList1.size())), calList2.size());
			for (int row = 0; row < max; row++) {
				if (calList.size() > row) {
					System.out.print(calList.get(row));
				} else {
					System.out.print(EMPTY_DAYS);
				}
				System.out.print("\t");
				if (calList1.size() > row) {
					System.out.print(calList1.get(row));
				} else {
					System.out.print(EMPTY_DAYS);
				}
				System.out.print("\t");
				if (calList2.size() > row) {
					System.out.print(calList2.get(row));
				} else {
					System.out.print(EMPTY_DAYS);
				}
				System.out.println();
			}
			System.out.println();
		}
	}

}
