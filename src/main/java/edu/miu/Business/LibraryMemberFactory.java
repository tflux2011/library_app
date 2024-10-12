package edu.miu.Business;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import edu.miu.DataAccess.DataAccessFacade;
import edu.miu.DataAccess.StorageManager;
import edu.miu.Model.Address;
import edu.miu.Model.LibraryMember;

public class LibraryMemberFactory {
    public static String addMember(String firstName, String lastName, String phone, String street, String city, String state, String zip) {
    	StorageManager manager = new DataAccessFacade();
        Map<Integer, LibraryMember> membersMap = manager.readMembersFromStorage();
        
        int memberId = membersMap.size() + 1;

        LibraryMember member = new LibraryMember(memberId, firstName, lastName, phone, street, city, state, zip);
        membersMap.put(memberId, member);
        
        manager.saveMembersToStorage(membersMap);

        return "Library Member added successfully.";
    }

    public static LibraryMember findMemberById(int memberId) {
    	StorageManager manager = new DataAccessFacade();
        Map<Integer, LibraryMember> membersMap = manager.readMembersFromStorage();
        return membersMap.values().stream()
                .filter(member -> member.getMemberId() == memberId)
                .findFirst()
                .orElse(null);
    }
    
    public static List<LibraryMember> getAllMembers() {
    	StorageManager manager = new DataAccessFacade();
        Map<Integer, LibraryMember> membersMap = manager.readMembersFromStorage();
        
        if (membersMap.isEmpty()) {
            throw new IllegalStateException("No library members found.");
        }
        
        return membersMap.values().stream()
                .collect(Collectors.toUnmodifiableList());
    }
}
