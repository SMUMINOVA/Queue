package queue;
/*

Model:
    [a1, a2...an]
    n
Inv:
    n >= 0
    forall i = 1..n: a[i] != null
*/

public interface Queue {

    /*
    Pred: true
    Post: R == n && n == n' && forall i = 0..n: a[i] == a'[i]
     */
    int size();

    /*
    Pred: true
    Post: R == (n > 0) && n == n' && forall i = 0..n: a[i] == a'[i]
     */
    boolean isEmpty();

    //Pred: el != null
    //Post: n == n' + 1 && a[n] == el && forall i = 0..n': a[i] == a'[i]
    void enqueue(Object e);

    /*
    Pred: n > 0
    Post: R == a'[1] && n == n' - 1 && forall i = 0..n: a[i] == a'[i]
     */
    Object dequeue();

    /*
    Pred: el != null
    Post: n == n' + 1 && a[1] == el && forall i = 1..n': a[i + 1] == a'[i]
     */
    void push(Object e);

    /*
    Pred: n > 0
    Post: R == a'[n'] && n == n' - 1 && forall i = 1..n: a[i] == a'[i]
     */
    Object remove();

    /*
    Pred: n > 0
    Post: R == a[n] && n == n' && forall i = 0..n: a[i] == a'[i]
    */
    Object element();

    /*
    Pred: n > 0
    Post: R == a[n] && n == n' && forall i = 1..n: a[i] == a'[i]
    */
    Object peek();

    /*
    Pred: true
    Post: n == 0 && forall i = 0..n: a[i] == a'[i]
     */
    void clear();

    /*
    Pred: el != null
    Post: R == (b.size > 0) && b = [i forall i that a[i] == el]
     */
    boolean contains(Object el);

    /*
    Pred: el != null
    Post: R == (b.size > 0) && b = [i forall i that a[i] == el] &&
          a[1, 2, ... min(b) - 1, min(b) + 1, ... n ]
     */
    boolean removeFirstOccurrence(Object e);
}
