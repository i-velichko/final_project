package org.velichko.finalproject.controller.command;

/**
 * @author Ivan Velichko
 *
 * The type Page.
 */
public class Page {

    private final int totalCount;
    private final int currentPage;
    private final int pageSize;

    /**
     * Instantiates a new Page.
     *
     * @param totalCount  the total count
     * @param currentPage the current page
     * @param pageSize    the page size
     */
    public Page(int totalCount, int currentPage, int pageSize) {
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    /**
     * Gets total count.
     *
     * @return the total count
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * Gets current page.
     *
     * @return the current page
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * Is first page boolean.
     *
     * @return the boolean
     */
    public boolean isFirstPage() {
        return currentPage == 1;
    }

    /**
     * Is last page boolean.
     *
     * @return the boolean
     */
    public boolean isLastPage() {
        return currentPage == pageCount();
    }

    /**
     * Page count int.
     *
     * @return the int
     */
    public int pageCount() {
        return (int) Math.ceil((double) totalCount / pageSize);
    }

}
