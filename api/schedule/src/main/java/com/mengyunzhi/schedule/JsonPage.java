package com.mengyunzhi.schedule;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.schedule.jsonView.CourseJsonView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class JsonPage<T> extends org.springframework.data.domain.PageImpl<T> {

    public JsonPage(final List<T> content, final Pageable pageable, final long total) {
        super(content, pageable, total);
    }


    public int getTotalPages() {
        return super.getTotalPages();
    }

    @JsonView(CourseJsonView.class)
    public int getSize() {
        return super.getSize();
    }

    public long getTotalElements() {
        return super.getTotalElements();
    }


    public boolean hasNext() {
        return super.hasNext();
    }


    public boolean isLast() {
        return super.isLast();
    }


    public boolean hasContent() {
        return super.hasContent();
    }

    @JsonView(CourseJsonView.class)
    public List<T> getContent() {
        return super.getContent();
    }
}
