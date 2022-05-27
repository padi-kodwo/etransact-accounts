package com.etransact.accounts.spec;


import com.etransact.accounts.entity.Account;
import com.etransact.accounts.entity.User;
import net.kaczmarzyk.spring.data.jpa.domain.DateBetween;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Or({
        @Spec(path = "number", params="number", spec = EqualIgnoreCase.class),
        @Spec(path = "firstName", params="first_name", spec = LikeIgnoreCase.class),
        @Spec(path = "type", params="type", spec = LikeIgnoreCase.class),
        @Spec(path = "createdAt", params={"created_after", "created_before"}, spec = DateBetween.class, config="yyyy-MM-dd"),

})
public interface SearchUAccountSpec extends Specification<Account> {
}
