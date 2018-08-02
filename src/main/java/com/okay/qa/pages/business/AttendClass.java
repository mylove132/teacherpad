package com.okay.qa.pages.business;

import com.okay.qa.domain.ElementEntity;
import com.okay.qa.pages.BasePage;
import com.okay.qa.utils.ResourceUrlUtils;
import org.springframework.stereotype.Component;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2018-04-09:16:15
 * Modify date: 2018-04-09:16:15
 */
@Component
public class AttendClass extends BasePage {

    public void clickLessonClassButton(){
        ElementEntity elementEntity = getElementEntity("ATTEND_LESSON_GROUP");
        click(elementEntity);
    }

    public void clickAttendClassButton(){
        ElementEntity elementEntity = getElementEntity("ATTEND_CLASS_GROUP");
        click(elementEntity);
    }

    public void clickCorrectingButton(){
        ElementEntity elementEntity = getElementEntity("ATTEND_CORRECT_GROUP");
        click(elementEntity);
    }
}
