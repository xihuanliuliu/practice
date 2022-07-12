package com.ai.aif.gitai.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.*;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CommonUtil {

  private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

  private final static Base64.Encoder encoder = Base64.getEncoder();

  private static final String _BR = "<br/>";

  //检查用户输入的规则名称是否为空，不为空的时候截取字符串
  public static String intercept255String(String inputString){
    if ( StringUtils.isBlank(inputString) ) {
      return null ;
    } else {
      //按照utf-8判断字符串长度，大于255时，截取前255个字节的字符返回
      int len=0;
      char c ;
      int i = 0 ;
      for( i=0 ; i <= inputString.length() - 1 ; i++ ) {
        c = inputString.charAt(i);
        if (c > 255) {
          if ( len >= 252) break ;
          len += 3;
        } else {
          if ( len >= 254) break ;
          len++;
        }
      }
      inputString = inputString.substring(0,i);
    }
    return inputString;
  }

  /**
   * 将日期中的斜线去掉
   */
  public static String cutDateStr(String date){
    if(date != null &&date.length()==10)
      return date.substring(0, 4) + date.substring(5, 7) + date.substring(8, 10);
    else
      return date;
  }
  /**
   * 将日期中间加上斜线
   */
  public static String addDateStr(String date){
    if(date != null &&date.length()==8)
      return date.substring(0, 4) + "/" + date.substring(4, 6) + "/" + date.substring(6, 8);
    else
      return date;
  }
  /**
   * 格式化日期时间格式为：年月日时分秒
   * @param dateTime
   * @return
   */
  public static String formatDateTime(String dateTime){
    if(dateTime != null &&dateTime.length()==14)
      return dateTime.substring(0, 4) + "/" + dateTime.substring(4, 6) + "/" + dateTime.substring(6, 8)+" " +
              dateTime.substring(8, 10) + ":" + dateTime.substring(10, 12) + ":" + dateTime.substring(12, 14);
    else
      return dateTime;
  }

  /**
   * 将日期转换成带中文的日期格式
   */
  public static String date2CN(String date){
    if(date != null &&date.length()==8)
      return date.substring(0, 4) + "年" + date.substring(4, 6) + "月" + date.substring(6, 8)+"日";
    else if(date!=null && date.length()==10)
      return date.substring(0, 4) + "年" + date.substring(5, 7) + "月" + date.substring(8, 10)+"日";
    else if(date!=null && date.length()==7)
      return date.substring(0, 4) + "年" + date.substring(5, 7) + "月" ;
    else
      return date;
  }
  /**
   * 传入一个路径，返回文件名
   * @param path
   * @return
   */
  public static String filenameOfPath(String path){
    String temp[] = path.split("\\\\");
    String fileName = temp[temp.length-1];
    return fileName;
  }


  /**
   * 功能描述：列出某文件夹及其子文件夹下面的文件，并可根据扩展名过滤
   *
   * @param path
   *            文件夹
   */
  public static void list(File path) {
    if (!path.exists()) {
      System.out.println("文件名称不存在!");
    } else {
      if (path.isFile()) {
        if (path.getName().toLowerCase().endsWith(".pdf")
                || path.getName().toLowerCase().endsWith(".doc")
                || path.getName().toLowerCase().endsWith(".chm")
                || path.getName().toLowerCase().endsWith(".html")
                || path.getName().toLowerCase().endsWith(".htm")) {// 文件格式
          System.out.println(path);
          System.out.println(path.getName());
        }
      } else {
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
          list(files[i]);
        }
      }
    }
  }

  /**
   * 功能描述：分割字符串
   *
   * @param str
   *            String 原始字符串
   * @param splitsign
   *            String 分隔符
   * @return String[] 分割后的字符串数组
   */
  @SuppressWarnings("unchecked")
  public static String[] split(String str, String splitsign) {
    int index;
    if (str == null || splitsign == null) {
      return null;
    }
    ArrayList al = new ArrayList();
    while ((index = str.indexOf(splitsign)) != -1) {
      al.add(str.substring(0, index));
      str = str.substring(index + splitsign.length());
    }
    al.add(str);
    return (String[]) al.toArray(new String[0]);
  }

  /**
   * 功能描述：替换字符串
   *
   * @param from
   *            String 原始字符串
   * @param to
   *            String 目标字符串
   * @param source
   *            String 母字符串
   * @return String 替换后的字符串
   */
  public static String replace2(String from, String to, String source) {
    if (source == null || from == null || to == null)
      return null;
    StringBuffer str = new StringBuffer("");
    int index = -1;
    while ((index = source.indexOf(from)) != -1) {
      str.append(source.substring(0, index) + to);
      source = source.substring(index + from.length());
      index = source.indexOf(from);
    }
    str.append(source);
    return str.toString();
  }


  /**
   * 功能描述：返回指定字节长度的字符串
   *
   * @param str
   *            String 字符串
   * @param length
   *            int 指定长度
   * @return String 返回的字符串
   */
  public static String toLength(String str, int length) {
    if (str == null) {
      return null;
    }
    if (length <= 0) {
      return "";
    }
    try {
      if (str.getBytes("GBK").length <= length) {
        return str;
      }
    } catch (Exception e) {
    }
    StringBuffer buff = new StringBuffer();

    int index = 0;
    char c;
    length -= 3;
    while (length > 0) {
      c = str.charAt(index);
      if (c < 128) {
        length--;
      } else {
        length--;
        length--;
      }
      buff.append(c);
      index++;
    }
    buff.append("...");
    return buff.toString();
  }

  /**
   * 功能描述：判断是否为整数
   *
   * @param str
   *            传入的字符串
   * @return 是整数返回true,否则返回false
   */
  public static boolean isInteger(String str) {
    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
    return pattern.matcher(str).matches();
  }

  /**
   * 判断是否为浮点数，包括double和float
   *
   * @param str
   *            传入的字符串
   * @return 是浮点数返回true, 否则返回false
   */
  public static boolean isDouble(String str) {
    Pattern pattern = Pattern.compile("^[-\\+]?\\d+\\.\\d+$");
    return pattern.matcher(str).matches();
  }

  public static void bubbleSort(int[] arr, boolean ascending) { //exchange标志表示为升序排序还是降序排序
    boolean flag = true; //加一个标志位，记录上一次是否发生了交换，如果是，我们则进行下一轮，如果没有，说明已经冒泡好了
    for (int i = 1; i < arr.length && flag; i++) { //控制次数，第几趟排序，只需要n-1趟，有交换时进行，只有flag=false就说明上一次一个元素都没有进行交换
            /*System.out.print("第"+i+"次遍历：");
            for (int i1 : arr) {
                System.out.print(i1+" ");
            }
            System.out.println();*/
      flag = false; //假定未交换
      for (int j = 0; j < arr.length - i; j++) {
        if (ascending ? arr[j] > arr[j + 1] : arr[j] < arr[j + 1]) { //控制升序还是降序
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
          flag = true;
        }
      }
    }
  }

  //冒泡排序 -- 默认不传参升序
  public static void bubbleSort(int[] arr) {
    bubbleSort(arr, true);
  }

  public static void quickSort(int[] arr) {
    quickSort(arr, true);
  }

  public static void quickSort(int[] arr, boolean ascending) {
    if (ascending) {
      quickSort(arr, 0, arr.length - 1, true);
    } else {
      quickSort(arr, 0, arr.length - 1, false);
    }
  }

  public static void quickSort(int[] arr, int begin, int end, boolean ascending) {
    if (ascending)
      quickSort(arr, begin, end);
    else
      quickSortDescending(arr, begin, end);
  }

  //快排序升序 -- 默认
  public static void quickSort(int[] arr, int begin, int end) {
    if (begin > end) { //结束条件
      return;
    }
    int base = arr[begin];
    int i = begin, j = end;
    while (i < j) { // 两个哨兵（i左边，j右边）没有相遇
      while (arr[j] >= base && i < j) { //哨兵j没找到比base小的
        j--;
      }
      while (arr[i] <= base && i < j) { //哨兵i没找到比base大的
        i++;
      }
      if (i < j) { //如果满足条件则交换
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }
    //最后将基准为与i和j相等位置的数字交换
    arr[begin] = arr[i];
    arr[i] = base;
    quickSort(arr, begin, i - 1); //递归调用左半数组
    quickSort(arr, i + 1, end); //递归调用右半数组
  }

  //快排序降序
  public static void quickSortDescending(int[] arr, int begin, int end) {
    if (begin > end) { //结束条件
      return;
    }
    int base = arr[begin];
    int i = begin, j = end;
    while (i < j) { // 两个哨兵（i左边，j右边）没有相遇
      while (arr[j] <= base && i < j) { //哨兵j没找到比base大的
        j--;
      }
      while (arr[i] >= base && i < j) { //哨兵i没找到比base小的
        i++;
      }
      if (i < j) { //如果满足条件则交换
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }
    //最后将基准为与i和j相等位置的数字交换
    arr[begin] = arr[i];
    arr[i] = base;
    quickSortDescending(arr, begin, i - 1); //递归调用左半数组
    quickSortDescending(arr, i + 1, end); //递归调用右半数组
  }

  /**
   * 判断是不是合法字符 c 要判断的字符
   */
  public static boolean isLetter(String str) {
    if (str == null || str.length() < 0) {
      return false;
    }
    Pattern pattern = Pattern.compile("[\\w\\.-_]*");
    return pattern.matcher(str).matches();
  }

  /**
   * 从指定的字符串中提取Email content 指定的字符串
   *
   * @param content
   * @return
   */
  public static String parse(String content) {
    String email = null;
    if (content == null || content.length() < 1) {
      return email;
    }
    // 找出含有@
    int beginPos;
    int i;
    String token = "@";
    String preHalf = "";
    String sufHalf = "";

    beginPos = content.indexOf(token);
    if (beginPos > -1) {
      // 前项扫描
      String s = null;
      i = beginPos;
      while (i > 0) {
        s = content.substring(i - 1, i);
        if (isLetter(s))
          preHalf = s + preHalf;
        else
          break;
        i--;
      }
      // 后项扫描
      i = beginPos + 1;
      while (i < content.length()) {
        s = content.substring(i, i + 1);
        if (isLetter(s))
          sufHalf = sufHalf + s;
        else
          break;
        i++;
      }
      // 判断合法性
      email = preHalf + "@" + sufHalf;
      if (isEmail(email)) {
        return email;
      }
    }
    return null;
  }

  /**
   * 功能描述：判断输入的字符串是否符合Email样式.
   *
   * @param email
   *            传入的字符串
   * @return 是Email样式返回true,否则返回false
   */
  public static boolean isEmail(String email) {
    if (email == null || email.length() < 1 || email.length() > 256) {
      return false;
    }
    Pattern pattern = Pattern
            .compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
    return pattern.matcher(email).matches();
  }

  /**
   * 功能描述：判断输入的字符串是否为纯汉字
   *
   * @param str
   *            传入的字符窜
   * @return 如果是纯汉字返回true,否则返回false
   */
  public static boolean isChinese(String str) {
    Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
    return pattern.matcher(str).matches();
  }

  /**
   * 功能描述：是否为空白,包括null和""
   *
   * @param str
   * @return
   */
  public static boolean isBlank(String str) {
    return str == null || str.trim().length() == 0;
  }

  /**
   * 功能描述：判断是否为质数
   *
   * @param x
   * @return
   */
  public static boolean isPrime(int x) {
    if (x <= 7) {
      if (x == 2 || x == 3 || x == 5 || x == 7)
        return true;
    }
    int c = 7;
    if (x % 2 == 0)
      return false;
    if (x % 3 == 0)
      return false;
    if (x % 5 == 0)
      return false;
    int end = (int) Math.sqrt(x);
    while (c <= end) {
      if (x % c == 0) {
        return false;
      }
      c += 4;
      if (x % c == 0) {
        return false;
      }
      c += 2;
      if (x % c == 0) {
        return false;
      }
      c += 4;
      if (x % c == 0) {
        return false;
      }
      c += 2;
      if (x % c == 0) {
        return false;
      }
      c += 4;
      if (x % c == 0) {
        return false;
      }
      c += 6;
      if (x % c == 0) {
        return false;
      }
      c += 2;
      if (x % c == 0) {
        return false;
      }
      c += 6;
    }
    return true;
  }

  /**
   * 功能描述：过滤特殊字符
   *
   * @param src
   * @return
   */
  public static String encoding(String src) {
    if (src == null)
      return "";
    StringBuilder result = new StringBuilder();
    if (src != null) {
      src = src.trim();
      for (int pos = 0; pos < src.length(); pos++) {
        switch (src.charAt(pos)) {
          case '\"':
            result.append("&quot;");
            break;
          case '<':
            result.append("&lt;");
            break;
          case '>':
            result.append("&gt;");
            break;
          case '\'':
            result.append("&apos;");
            break;
          case '&':
            result.append("&amp;");
            break;
          case '%':
            result.append("&pc;");
            break;
          case '_':
            result.append("&ul;");
            break;
          case '#':
            result.append("&shap;");
            break;
          case '?':
            result.append("&ques;");
            break;
          default:
            result.append(src.charAt(pos));
            break;
        }
      }
    }
    return result.toString();
  }

  public static boolean isPalindrome(int x) {
    //情况1
    if (x < 0 || (x % 10 == 0 && x != 0)) {
      return false;
    }

    int res = 0;
    while (x > res) {
      res = res * 10 + x % 10;
      x /= 10;
    }

    // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
    // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
    // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
    return x == res || x == res / 10;
  }

  public static void zip(String args[]) throws IOException {
    if (args.length < 2) {
      System.err.println("usage: java ZipIt Zip.zip file1 file2 file3");
      System.exit(-1);
    }

    File zipFile = new File(args[0]);
    if (zipFile.exists()) {
      System.err.println("Zip file already exists, please try another");
      System.exit(-2);
    }

    FileOutputStream fos = new FileOutputStream(zipFile);
    ZipOutputStream zos = new ZipOutputStream(fos);

    int bytesRead;
    byte[] buffer = new byte[1024];
    CRC32 crc = new CRC32();
    for (int i = 1, n = args.length; i < n; i++) {
      String name = args[i];
      File file = new File(name);

      if (!file.exists()) {
        System.err.println("Skipping: " + name);
        continue;
      }

      BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
      crc.reset();

      while ((bytesRead = bis.read(buffer)) != -1) {
        crc.update(buffer, 0, bytesRead);
      }
      bis.close();

      // Reset to beginning of input stream
      bis = new BufferedInputStream(new FileInputStream(file));
      ZipEntry entry = new ZipEntry(name);
      entry.setMethod(ZipEntry.STORED);
      entry.setCompressedSize(file.length());
      entry.setSize(file.length());
      entry.setCrc(crc.getValue());
      zos.putNextEntry(entry);

      while ((bytesRead = bis.read(buffer)) != -1) {
        zos.write(buffer, 0, bytesRead);
      }
      bis.close();
    }
    zos.close();
  }

  public String longestPalindrome(String s) {
    if (s == null) {
      return "";
    }
    int len = s.length();
    if (len < 2) {
      return s;
    }

    int maxLen = 1;
    int begin = 0;
    // dp[i][j] 表示 s[i..j] 是否是回文串
    boolean[][] dp = new boolean[len][len];
    // 初始化：所有长度为 1 的子串都是回文串
    for (int i = 0; i < len; i++) {
      dp[i][i] = true;
    }

    char[] charArray = s.toCharArray();
    // 递推开始
    // 先枚举子串长度
    for (int L = 2; L <= len; L++) {
      // 枚举左边界，左边界的上限设置可以宽松一些
      for (int i = 0; i < len; i++) {
        // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
        int j = L + i - 1;
        // 如果右边界越界，就可以退出当前循环
        if (j >= len) {
          break;
        }

        if (charArray[i] != charArray[j]) {
          dp[i][j] = false;
        } else {
          if (j - i < 3) {
            dp[i][j] = true;
          } else {
            dp[i][j] = dp[i + 1][j - 1];
          }
        }

        // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
        if (dp[i][j] && j - i + 1 > maxLen) {
          maxLen = j - i + 1;
          begin = i;
        }
      }
    }
    return s.substring(begin, begin + maxLen);
  }

  public boolean isMatchNew(String s, String p) {
    int sLen = s.length(), pLen = p.length();
    boolean[][] memory = new boolean[sLen + 1][pLen + 1];
    memory[0][0] = true;
    for (int i = 0; i <= sLen; i++) {
      for (int j = 1; j <= pLen; j++) {
        if (p.charAt(j - 1) == '*') {
          memory[i][j] = memory[i][j - 2] || (i > 0 && (s.charAt(i - 1) == p.charAt(j - 2) ||
                  p.charAt(j - 2) == '.') && memory[i - 1][j]);
        } else {
          memory[i][j] = i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')
                  && memory[i - 1][j - 1];
        }
      }
    }
    return memory[sLen][pLen];
  }

  public static List<Integer> findSubstring(String s, String... words) {
    List<Integer> list = new ArrayList<>();
    if (s.length() == 0 || words.length == 0)
      return list;
    int len = words[0].length();
    if (s.length() < words.length * len)
      return list;
    int idx = -1;
    char[] sCharS = s.toCharArray();
    Set<Integer> allSet = new HashSet<Integer>();// 存储在字符串S所有匹配的下标 去重 排序
    Map<String, Integer> wordMap = new HashMap<>();// 存储 word中各个单词的个数
    Map<Integer, String> idxMap = new HashMap<>();// 存储字符串s各下标对应的单词
    for (String word : words) {
      if (wordMap.containsKey(word)) {
        wordMap.put(word, wordMap.get(word) + 1);
        continue;
      }
      char[] tem = word.toCharArray();
      wordMap.put(word, 1);
    }

    String word;
    int slideLen = len * words.length;// 滑块长度
    int n;// 临时变量
    Map<String, Integer> temWordMap = new HashMap<>();
    for (int k = 0; k < len; k++) {
      int flagNum = 0;// 表示滑块中有效的单词个数
      for (int i = -1, j = k; j <= sCharS.length - len; j += len) {
        if (allSet.contains(j)) {
          if (i == -1) {// 初始化滑块
            i = j;
            flagNum = 0;
            temWordMap.clear();
            temWordMap.putAll(wordMap);
          }
          // 滑块长度增加 在尾部添加
          word = idxMap.get(j);
          n = temWordMap.get(word) - 1;
          temWordMap.put(word, n);
          if (n >= 0)
            flagNum++;
          if (j - i >= slideLen) {// 滑块长度减小 吐出头部数据
            word = idxMap.get(i);
            n = temWordMap.get(word) + 1;
            temWordMap.put(word, n);
            if (n > 0)
              flagNum--;
            i += len;
          }
          if (flagNum == words.length)
            list.add(i);
        } else {
          i = -1;// j所在的位置不是给定的单词 ，销毁滑块
        }
      }
    }
    return list;
  }

  public int longestValidParentheses(String s) {
    char[] chars = s.toCharArray();
    return Math.max(calc(chars, 0, 1, chars.length, '('), calc(chars, chars.length - 1, -1, -1, ')'));
  }

  private static int calc(char[] chars, int i, int flag, int end, char cTem) {
    int max = 0, sum = 0, currLen = 0, validLen = 0;
    for (; i != end; i += flag) {
      sum += (chars[i] == cTem ? 1 : -1);
      currLen++;
      if (sum < 0) {
        max = max > validLen ? max : validLen;
        sum = 0;
        currLen = 0;
        validLen = 0;
      } else if (sum == 0) {
        validLen = currLen;
      }
    }
    return max > validLen ? max : validLen;
  }

  public boolean isMatch(String s, String p) {
    if (p.equals(".*")) {
      return true;
    }
    int sl = s.length();
    int pl = p.length();
    int[][] dp = new int[sl + 1][pl + 1];
    dp[0][0] = 1;
    for (int i = 1; i < sl + 1; i++) {
      dp[i][0] = 0;
    }
    dp[0][1] = 0;
    for (int j = 2; j < pl + 1; j++) {
      if (p.substring(j - 1, j + 1 - 1).equals("*")) {
        if (dp[0][j - 1] == 1 || dp[0][j - 2] == 1) {
          dp[0][j] = 1;
        } else {
          dp[0][j] = 0;
        }
      } else {
        dp[0][j] = 0;
      }
    }
    for (int i = 1; i < sl + 1; i++) {
      for (int j = 1; j < pl + 1; j++) {
        if (p.substring(0, j).equals(".*")) {
          dp[i][j] = 1;
        } else {
          if (s.substring(i - 1, i + 1 - 1).equals(p.substring(j - 1, j + 1 - 1)) || p.substring(j - 1, j + 1 - 1).equals(".")) {
            if (dp[i - 1][j - 1] == 1) {
              dp[i][j] = 1;
            } else {
              dp[i][j] = 0;
            }
          } else if (p.substring(j - 1, j + 1 - 1).equals("*")) {
            if (dp[i][j - 1] == 1 || dp[i][j - 2] == 1) {
              dp[i][j] = 1;
            } else if (s.substring(i - 1, i + 1 - 1).equals(p.substring(j - 1 - 1, j + 1 - 1 - 1)) && dp[i - 1][j] == 1) {
              dp[i][j] = 1;
            } else if (p.substring(j - 1 - 1, j + 1 - 1 - 1).equals(".")) {
              int flag = 0;
              for (int k = 0; k <= i; k++) {
                if (dp[k][j - 2] == 1) {
                  flag = 1;
                }
              }
              if (flag == 1) {
                dp[i][j] = 1;
              } else {
                dp[i][j] = 0;
              }
            } else {
              dp[i][j] = 0;
            }
          } else {
            dp[i][j] = 0;
          }
        }
        System.out.println("i:" + i + ",j:" + j + ":  " + dp[i][j]);
      }
    }
    if (dp[sl][pl] == 1) {
      return true;
    } else {
      return false;
    }
  }


  /**
   * 图片压缩
   * @param image
   * @param mimeType
   * @param expectSize
   * @return
   * @throws IOException
   */
  public static byte[] compressToExpectSize(byte[] image, String mimeType, int expectSize) throws IOException {
    float scale = calculateScale(image, expectSize);
    return compressWithScale(image, mimeType, scale);
  }

  private static float calculateScale(byte[] original, int expectSize) {
    if (expectSize <= 0) {
      return 1;
    } else if (null == original) {
      return 0;
    }

    if (expectSize > original.length) {
      return 1.0f;
    }

    return (expectSize * 1.0f) / original.length;
  }

  public static byte[] compressWithScale(byte[] originalImage, String mimeType, float scale) throws IOException {
    logger.info("originalImage size is " + (originalImage == null ? -1 : originalImage.length));
    if (originalImage == null) {
      return null;
    }
    BufferedImage bi = ImageIO.read(new ByteArrayInputStream(originalImage));
    // 压缩图片的宽，高
    int width = (int) (bi.getWidth() * scale); // 源图宽度
    int height = (int) (bi.getHeight() * scale); // 源图高度
    Image image = bi.getScaledInstance(width, height, Image.SCALE_FAST);
    BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics g = tag.getGraphics();
    g.setColor(Color.RED);
    g.drawImage(image, 0, 0, null); // 绘制处理后的图
    g.dispose();
    ByteArrayOutputStream bOut = new ByteArrayOutputStream();
    ImageOutputStream ios = ImageIO.createImageOutputStream(bOut);
    ImageWriter writer = ImageIO.getImageWritersByFormatName(mimeType).next();
    writer.setOutput(ios);
    ImageWriteParam param = writer.getDefaultWriteParam();
    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // Needed see javadoc\
    //设置压缩比
    param.setCompressionQuality(scale); // Highest quality
    writer.write(null, new IIOImage(tag, null, null), param);
    return bOut.toByteArray();
  }

  public final static String base64Encode(byte[] byteArray) {
    return encoder.encodeToString(byteArray);
  }

  public static String compressAndBase64Encode(byte[] originalImage, String mimeType, int expectSize) throws IOException {
    byte[] compressedImage = compressToExpectSize(originalImage, mimeType, expectSize);
    return base64Encode(compressedImage);
  }


  public static byte[] getByteByPic(String imageUrl) throws IOException {
    try (InputStream input = CommonUtil.class.getClassLoader().getResourceAsStream(imageUrl)) {
      BufferedInputStream bis = new BufferedInputStream(input);
      BufferedImage bm = ImageIO.read(bis);
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      String type = imageUrl.substring(imageUrl.length() - 3);

      ImageIO.write(bm, type, bos);
      bos.flush();
      byte[] data = bos.toByteArray();
      return data;
    }
  }

    /**
     * byte数组到图片
   * @param data
     * @param path
     * @throws IOException
   */
  public static void byte2image(byte[] data, String path) throws IOException {
    if (data.length < 3 || path.equals("")) {
      return;
    }
    try (FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path))) {
      imageOutput.write(data, 0, data.length);
    }
  }

  public static void main(String[] args) throws IOException {
    long timeStart = System.currentTimeMillis();
    String imgLocalUrl = "E:/work/video/gitai.png";
    byte[] imgBytes = getByteByPic(imgLocalUrl);
    assert imgBytes != null;
    byte[] resultImg = compressToExpectSize(imgBytes, "jpg", 200 * 1024);
    assert resultImg != null;
    byte2image(resultImg, "E:\\work\\video\\test.jpg");

    System.out.println(base64Encode(resultImg));
    long timeEnd = System.currentTimeMillis();
    System.out.println("耗时：" + (timeEnd - timeStart));
  }

}
