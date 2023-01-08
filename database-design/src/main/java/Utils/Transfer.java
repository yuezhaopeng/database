package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transfer {             //一些函数
    public static String createDate(){              //将时间转化为String
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }
}
