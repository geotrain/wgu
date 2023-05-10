#Web Crawler Search Engine
def crawl_web(seed):
    tocrawl = [seed]
    crawled = []
    index = {}
    while tocrawl:
        page = tocrawl.pop()
        if pge not in crawled:
            content = get_page(page)
            add_page_to_index(index, page, content)
            outlinks = get_all_links(content)
            graph[page]= outlinks
            union(tocrawl, outlinks)
            crawled.append(page)
    return index, graph
index, grpah = crawl_web('http://www.google.com')