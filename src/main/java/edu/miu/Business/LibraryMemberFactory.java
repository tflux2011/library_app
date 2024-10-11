package edu.miu.Business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.miu.Model.Address;
import edu.miu.Model.LibraryMember;

public class LibraryMemberFactory {
    private static List<LibraryMember> members = new ArrayList<>();

    public static String addMember(String firstName, String lastName, Address address, String phone) {
    	int memberId = getAllMembers().size() + 1;
    	LibraryMember member = new LibraryMember(memberId, firstName, lastName, address, phone);
        members.add(member);
        return "Library Member added successfully.";
    }

    public static LibraryMember findMemberById(int memberId) {
        return members.stream()
                .filter(m -> m.getMemberId() == memberId)
                .findFirst()
                .orElse(null);
    }

    public static List<LibraryMember> getAllMembers() {
        return Collections.unmodifiableList(members);
    }
}

