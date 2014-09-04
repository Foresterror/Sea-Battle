package networks;

/**
 * Посылает раз в 20 секунд пустой Message чтобы соединение не отвалилось
 *
 * @author Alexander Vlasov
 */
public class NoTimeOut implements Runnable {
    private MessageSender sender;

    //    private boolean interrupt;
    public NoTimeOut(MessageSender sender) {
        this.sender = sender;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                System.out.println("NoTimeOut (" + Thread.currentThread().getName() + ") InterruptedException: sleep interrupted ");
                break;
            }

//            System.out.println("NoTimeOut (" + Thread.currentThread().getName() + ") interrupt == " + interrupt);
//            if (!interrupt) {
                sender.sendMessage(new Message(MessageType.NOTIMEOUT));
//            } else {
//                System.out.println("NoTimeOut (" + Thread.currentThread().getName() + ") must return = " + interrupt);
//                break;
//            }

        }
        System.out.println("NoTimeOut (" + Thread.currentThread().getName() + ") returns");
    }

//    public void interrupt() {
//        System.out.println("NoTimeOut (" + Thread.currentThread().getName() + ")set interrupt = true");
//        interrupt = true;
//        Thread.currentThread().interrupt();
//    }
}
