package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private Scanner sc;

    private long lastWiseSayingId;
    private List<WiseSaying> wiseSayings = new ArrayList<>();

    public App(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");

            String cmd = sc.nextLine().trim();

            if (cmd.isEmpty()) continue;
            Rq rq = new Rq(cmd);

            switch (rq.getActionCode()) {
                case "등록":
                    System.out.print("명언 : ");
                    String content = sc.nextLine().trim();
                    System.out.print("작가 : ");
                    String authorName = sc.next().trim();
                    long id = ++lastWiseSayingId;

                    wiseSayings.add(new WiseSaying(id, content, authorName));

                    System.out.printf("%d번 명언이 등록되었습니다\n", id);
                    break;
                case "삭제":
                    id = rq.getIntParam("id", -1);

                    if (id == -1) {
                        System.out.println("id(정수)를 입력해주세요");
                        return;
                    }

                    WiseSaying search = null;

                    for (WiseSaying wiseSaying : wiseSayings) {
                        if (wiseSaying.getId() == id) {
                            search = wiseSaying;
                            break;
                        }
                    }

                    if (search == null) {
                        System.out.printf("%d번 명언은 존재하지 않습니다\n", id);
                        return;
                    }

                    wiseSayings.remove(search);
                    System.out.printf("%d번 명언이 삭제되었습니다\n", id);
                    break;


                case "목록":
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("-".repeat(30));

                    for (int i = wiseSayings.size() - 1; i >= 0; --i) {
                        WiseSaying wiseSaying = wiseSayings.get(i);

                        System.out.printf("%d / %s / %s\n",
                                wiseSaying.getId(), wiseSaying.getAuthorName(), wiseSaying.getContent());
                    }
                case "종료":
                    System.out.println("프로그램이 종료되었습니다");
                    return;
                default:
                    System.out.printf("%s(은)는 올바르지 않은 명령입니다\n", cmd);
            }
        }
    }
}
