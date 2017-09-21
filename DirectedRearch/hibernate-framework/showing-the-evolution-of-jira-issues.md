**09/21/2017**

## Get Resolution Date 

``` java
issue.getField("resolutiondate") == null ? null : issue.getField("resolutiondate").getValue() == null ? null : parseDateFromString(String.valueOf(issue.getField("resolutiondate").getValue())),
```


```java
	private Date parseDateFromString(String s) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		Date date = null;
		try {
			date = format.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
```

**Reference**
[Link01 Stackoverflow](https://stackoverflow.com/questions/15433377/how-parse-2013-03-13t2059310000-date-string-to-date)
https://developer.android.com/reference/java/text/SimpleDateFormat.html
http://www.java2s.com/Tutorial/Java/0040__Data-Type/SimpleDateFormat.htm
```java
                 yyyy-MM-dd 1969-12-31
                 yyyy-MM-dd 1970-01-01
           yyyy-MM-dd HH:mm 1969-12-31 16:00
           yyyy-MM-dd HH:mm 1970-01-01 00:00
          yyyy-MM-dd HH:mmZ 1969-12-31 16:00-0800
          yyyy-MM-dd HH:mmZ 1970-01-01 00:00+0000
   yyyy-MM-dd HH:mm:ss.SSSZ 1969-12-31 16:00:00.000-0800
   yyyy-MM-dd HH:mm:ss.SSSZ 1970-01-01 00:00:00.000+0000
 yyyy-MM-dd'T'HH:mm:ss.SSSZ 1969-12-31T16:00:00.000-0800
 yyyy-MM-dd'T'HH:mm:ss.SSSZ 1970-01-01T00:00:00.000+0000
```


