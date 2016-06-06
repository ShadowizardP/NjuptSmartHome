package support;

public class TimeController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static String getPeriod(String per)
	{
		String period;
		if(15 <= Integer.parseInt(per.substring(11, 13)) && Integer.parseInt(per.substring(11, 13)) <= 44)
		{
			period = per.substring(1, 11) + "30";
		}
		else if(Integer.parseInt(per.substring(11, 12)) < 15)
		{
			period = per.substring(1, 11) + "00";
		}
		else
		{
			int year = Integer.parseInt(per.substring(1, 5));
			int month = Integer.parseInt(per.substring(5, 7));
			int day = Integer.parseInt(per.substring(7, 9));
			int hour = Integer.parseInt(per.substring(9, 11));
			if(hour < 23)
			{
				hour++;
				period = per.substring(1, 9) + PlusZero(hour) + "00";
			}
			else
			{
				period = dayPlusOne(year, month, day) + "0000";
			}
		}
		return period;
	}
	
	public static String dayPlusOne(int year,int month,int day)
	{
		String date = new String();
		if(day != daysInMonth(month, year))
		{
			day += 1;
		}
		else
		{
			day = 1;
			if(month != 12)
			{
				month +=1;
			}
			else
			{
				month = 1;
				year += 1;
			}
		}
		date = String.valueOf(year) + PlusZero(month) + PlusZero(day);
		return date;
	}
	
	private static String PlusZero(int num)
	{
		if(num < 10)
		{
			return ("0" + String.valueOf(num));
		}
		else
		{
			return String.valueOf(num);
		}
	}
	
	public static boolean isRunNian(int year)
	{
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static int daysInMonth(int month, int year)
	{
		int days = 0;
		switch (month)
		{
		case 1:
			days = 31;
			break;
		case 2:
			if (isRunNian(year))
			{
				days = 29;
			}
			else{
				days = 28;
			}
			break;
		case 3:
			days = 31;
			break;
		case 4:
			days = 30;
			break;
		case 5:
			days = 31;
			break;
		case 6:
			days = 30;
			break;
		case 7:
			days = 31;
			break;
		case 8:
			days = 31;
			break;
		case 9:
			days = 30;
			break;
		case 10:
			days = 31;
			break;
		case 11:
			days = 30;
			break;
		case 12:
			days = 31;
			break;
		default:
			break;
		}
		return days;
	}

}
