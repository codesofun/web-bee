package org.bee.webBee.annotation;

import org.bee.webBee.linker.Page;
import org.bee.webBee.processor.PageProcessor;
import org.bee.webBee.processor.Setting;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * data 2017-08-31   17:00
 * E-mail   sis.nonacosa@gmail.com
 * @author sis.nonacosa
 */
public class PageAnnotationProcess implements PageProcessor
{


    @Override
    public void process(Page page) throws IOException {

    }

    @Override
    public Setting getSetting() {
        return null;
    }
}
