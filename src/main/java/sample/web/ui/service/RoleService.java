package sample.web.ui.service;

import sample.web.ui.domain.Role;

import java.util.Comparator;

/**
 *
 * @author  Mark van Dalen
 *
 */

public class RoleService {

    public static class RoleComp implements Comparator<Role> {

        @Override
        public int compare(Role r1, Role r2) {
            return r1.getName().compareTo(r2.getName());
        }
    }
}
