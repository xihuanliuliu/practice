public class code {
    /**
     * 阿拉伯数字的中文读法，如123的读法为一百二十三，2001的读法为二千零一
     */

    public  static  String solu(int num){

        String result = "";
        if (num == 0)
        {
            return "零";
        }
        int _num = num;

        String[] chn_str = new String[] { "零","一", "二", "三", "四", "五", "六", "七", "八", "九" };
        String[] section_value = new String[] { "","万","亿","万亿"};
        String[] unit_value = new String[] { "", "十", "百", "千" };
        int section = _num % 10000;
        for (int i = 0; _num != 0 && i < 4; i++)
        {
            if (section == 0)
            {
                //0不需要考虑节权值，不能出现连续的“零”
                if (result.length() > 0 && result.substring(0, 1) != "零")
                {
                    result = "零" + result;
                }
                _num = _num / 10000;
                section = _num % 10000;
                continue;
            }
            result = section_value[i]+result;
            int unit = section % 10;
            for (int j = 0; j<4 ; j++)
            {
                if (unit == 0)
                {
                    //0不需要考虑位权值，不能出现联系的“零”，每节最后的0不需要
                    if (result.length() > 0 && result.substring(0, 1) != "零" && result.substring(0, 1) != section_value[i])
                    {
                        result = "零" + result;
                    }
                }
                else
                {
                    result = chn_str[unit] + unit_value[j] + result;
                }
                section = section / 10;
                unit = section % 10;
            }
            _num = _num / 10000;
            section = _num % 10000;
        }

        while (result.length() > 0 && result.substring(0, 1).equals("零"))
        {
            //清理最前面的"零"
            result = result.substring(1);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solu(1231));
        System.out.println(solu(1223233100));
        System.out.println(solu(11123100));
    }


}
