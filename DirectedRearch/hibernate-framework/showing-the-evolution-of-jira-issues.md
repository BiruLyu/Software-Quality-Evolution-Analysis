**09/21/2017**

## Get Resolution Date

```java
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
[https://developer.android.com/reference/java/text/SimpleDateFormat.html](https://developer.android.com/reference/java/text/SimpleDateFormat.html)  
[http://www.java2s.com/Tutorial/Java/0040\_\_Data-Type/SimpleDateFormat.htm](http://www.java2s.com/Tutorial/Java/0040__Data-Type/SimpleDateFormat.htm)

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



## Conclusion

I implemented three core function:

In the first function I sorted the issues' `created date` and `resolution date` separately, then used two pointers to go through all the date points, maintained a `cnt` variable indicating the current number of opening issues \(plus one when meeting start date, deduct 1 when meeting end date\), and store mapping of the specific date point to the number of current opening issue in a `Map`;
I chose the `TreeMap` data structure, so the time complexity of first function will be `O(nlongn)`.
Since the `TreeMap` offers floor method to retrieval greatest entry less or equal to the given input in `O(logn)`
Thus, the second function\(given specific timestamp\) and third function\(given a list of timestamp\) will return the number of current opening issue in `O(longn)` and `O(nlongn)` respectively.


### 

JIRA issue's description field only have two render options: **wiki style renderer(default)** and **default text renderer**
![](https://ws3.sinaimg.cn/large/006tKfTcgy1fjrqkvnf9lj30s80sidm6.jpg)
![](https://ws2.sinaimg.cn/large/006tKfTcgy1fjrqkhtbecj313k0kuwil.jpg)

[atlassian-jira-markdown-plugin](https://bitbucket.org/bbaker/atlassian-markdown/src)
> This plugin is no longer being maintained by myself. I no longer have the time to do it. I also have come to believe that Markdown in JIRA is not the right way to do rich text in issue descriptions. Please feel free to fork this open source code if you want Markdown in JIRA. There are 7 forks already. Hopefully 1 will emerge for those who want Markdown support