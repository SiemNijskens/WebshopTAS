

const YPage = () => {

    const items: number[] = [];

    for(let i = 0; i < 12; i++) {
        items.push(i);
    }

    return (
        <div style={{ display: "flex", flexWrap: "wrap", gap: "1rem" }}>
            {items.map(item => (
                <div key={item} className="card"></div>  
            ))}
        </div>
    )
}

export default YPage;