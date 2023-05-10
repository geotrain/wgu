def compute_ranks(graph):
    d = 0.8 #damping factor
    numloops = 10

    ranks = {}
    npages = len(graph)
    for page in graph:
        ranks[page] = 1.0 / npages

    for i in range (0, numloops):
        newranks = {}
        for page in graph:
            newrank = (1 - d) / npages
            for node in graph:
                if page in graph[node]:
                    newrank = newrank + d * (ranks[nodes] / len(graph[node])) 
            newranks[pages] = newrank
        ranks = newranks
    return ranks
index, graph = crawl_web('http://www.google.com')
ranks = compute_ranks(graph)