import java.util.*;

/**
 * @author biwenjie
 * @Description 用 add first 或 add last 这套新的 API 改写 Deque 的代码
 * @createTime 2020/06/14 13:39
 */
public class NewDeque {

    public void newDeque() {
        Deque<String> deque = new LinkedList<>();
        deque.offerFirst("a");
        deque.offerFirst("b");
        deque.offerFirst("c");
        System.out.println(deque);

        String str = deque.peekFirst();
        System.out.println(str);
        System.out.println(deque);

        while (deque.size() > 0) {
            System.out.println(deque.pollFirst());
        }
        System.out.println(deque);
    }

}
