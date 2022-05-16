import React from "react";
import DataTable from 'react-data-table-component';

import "./Table.css"

class Table extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            data: props.data
        }

    }

    render() {
        const columns = [
            {
                name: "ID",
                selector: row => Number(row.id),
                sortable: true
            },
            {
                name: "Birth year",
                selector: row => Number(row.birthYear),
                sortable: true
            },
            {
                name: "Education",
                selector: row => row.education,
                sortable: true
            },
            {
                name: "Marital status",
                selector: row => row.maritalStatus,
                sortable: true
            },
            {
                name: "Income",
                selector: row => Number(row.income),
                sortable: true
            },
            {
                name: "Kids",
                selector: row => Number(row.kidsAmount),
                sortable: true
            },
            {
                name: "Teens",
                selector: row => Number(row.teensAmount),
                sortable: true
            },
            {
                name: "Date of enrollment",
                selector: row => {
                    let dateItems = row.enrollmentDate.split("-");
                    return dateItems[2] + "-" + dateItems[1] + "-" + dateItems[0];
                },
                sortable: true
            },
            {
                name: "Recency",
                selector: row => Number(row.recency),
                sortable: true
            },
            {
                name: "Complains",
                selector: row => Number(row.complains),
                sortable: true
            },
            {
                name: "Wine bought",
                selector: row => Number(row.wineAmount),
                sortable: true
            },
            {
                name: "Fruits bought",
                selector: row => Number(row.fruitsAmount),
                sortable: true
            },
            {
                name: "Meat bought",
                selector: row => Number(row.meatAmount),
                sortable: true
            },
            {
                name: "Fish bought",
                selector: row => Number(row.fishAmount),
                sortable: true
            },
            {
                name: "Sweet bought",
                selector: row => Number(row.sweetAmount),
                sortable: true
            },
            {
                name: "Gold bought",
                selector: row => Number(row.goldAmount),
                sortable: true
            },
            {
                name: "Discount purchases",
                selector: row => Number(row.discountPurchasesAmount),
                sortable: true
            },
            {
                name: "Campaign 1",
                selector: row => Number(row.acceptedCampaign1),
                sortable: true
            },
            {
                name: "Campaign 2",
                selector: row => Number(row.acceptedCampaign2),
                sortable: true
            },
            {
                name: "Campaign 3",
                selector: row => Number(row.acceptedCampaign3),
                sortable: true
            },
            {
                name: "Campaign 4",
                selector: row => Number(row.acceptedCampaign4),
                sortable: true
            },
            {
                name: "Campaign 5",
                selector: row => Number(row.acceptedCampaign5),
                sortable: true
            },
            {
                name: "Last campaign",
                selector: row => Number(row.response),
                sortable: true
            },
            {
                name: "Web purchases",
                selector: row => Number(row.webPurchasesAmount),
                sortable: true
            },
            {
                name: "Catalog purchases",
                selector: row => Number(row.catalogPurchasesAmount),
                sortable: true
            },
            {
                name: "Store purchases",
                selector: row => Number(row.storePurchasesAmount),
                sortable: true
            },
            {
                name: "Website visits",
                selector: row => Number(row.visitsAmount),
                sortable: true
            }
        ];

        return (
            <div>
                <DataTable
                    columns={columns}
                    data={this.state.data}
                    pagination={true}
                />
            </div>

        );
    }
}

export default Table;