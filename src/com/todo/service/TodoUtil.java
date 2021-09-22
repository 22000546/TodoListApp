package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "======== 새로운 항목 추가 ========\n"
				+ "제목을 입력하세요. : ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("제목은 중복될 수 없습니다.");
			return;
		}
		
		System.out.println("설명을 입력하세요. : ");
		desc = sc.next();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "======== 기존 항목 삭제 ========\n"
				+ "선택할 항목의 제목을 입력해주세요. : ");
		
		String title = sc.next();
		
		int num = 0;
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				num ++;
				break;
			}
		}
		if(num == 0) {
			System.out.println("해당하는 항목이 없습니다.");
		} else {
			System.out.println("해당 항목이 성공적으로 삭제되었습니다.");
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "======== 기존 항목 수정 ========\n"
				+ "수정하고 싶은 항목의 제목을 입력해주세요. : ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("해당하는 항목이 없습니다.");
			return;
		}

		System.out.println("새로운 제목을 입력해주세요. : ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목은 중복될 수 없습니다.");
			return;
		}
		
		System.out.println("새로운 설명을 입력해주세요. : ");
		String new_description = sc.next().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("항목이 성공적으로 수정되었습니다.");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("======== 전체 항목 보기 ========");
		for (TodoItem item : l.getList()) {
			System.out.println("[" + item.getTitle() + "] " + item.getDesc() + " (" + item.getCurrent_date() + ")");
		}
	}
}
