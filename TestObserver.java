import java.util.ArrayList;
import java.util.List;

/**
 * 定义了对象之间一对多的依赖关系，一个对象改变时，它的所有依赖者都能收到消息
 * 下面模拟一个消息订阅的例子
 */
public class TestObserver {
    public static void main(String[] args) {
        ObserverImpl o1 = new ObserverImpl("o1");
        ObserverImpl o2 = new ObserverImpl("o2");
        ObserverImpl o3 = new ObserverImpl("o3");
        SubjectImpl subject = new SubjectImpl();
        subject.registerObserver(o1);
        subject.registerObserver(o2);
        subject.registerObserver(o3);
        subject.setMsg("买买买");
    }


}

interface Subject{
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObserves();
}

interface Observer{
    void update(String msg);
}

class ObserverImpl implements Observer{

    private String name;

    public ObserverImpl(String name) {
        this.name = name;
    }

    @Override
    public void update(String msg) {
        System.out.println(name+"观察者发现依赖的对象设置了新的消息:   "+ msg);
    }
}

class SubjectImpl implements Subject{

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        notifyObserves();
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    // 消息
    private String msg;

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserves() {
        for (Observer observer : observers) {
            observer.update(msg);
        }
    }
}